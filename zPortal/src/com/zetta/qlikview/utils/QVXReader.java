package com.zetta.qlikview.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class QVXReader {

	private InputStream a = null;
	private InputStream b = null;
	
	private String d = null;
	private int e = 0;
	private long f ;
	private int g = 0;
	private boolean h;
	private byte i[];
	private String j[];
	private Document headerDoc = null;
	private String dataString = "";

	public Logger logger = Logger.getLogger(getClass());

	public QVXReader(String fileName, String nullTag)
			throws IOException {
		
		//a = null;
		//b = null;
	
		e = 0;
		f = 0L;
		g = 0;
		h = false;
		j = new String[0];
		ArrayList arraylist = new ArrayList();
		d = nullTag;
		a = new BufferedInputStream(new FileInputStream(fileName));
		boolean flag = false;
		e = 0;
		try {
			int k;
			while ((k = a.read()) > 1) {
				arraylist.add(new Byte((byte) k));
				e++;
			}
			e++;
			k = a.read();
			e++;
			h = k == 30;
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
		i = new byte[arraylist.size()];
		int l = 0;
		for (Iterator iterator = arraylist.iterator(); iterator.hasNext();) {
			byte byte0 = ((Byte) iterator.next()).byteValue();
			i[l++] = byte0;
		}

		this.readHeader();
	}

	public QVXReader(String fileName) throws IOException {
		this(fileName, "");
	}

	private void readHeader() throws IOException {
		
		
		b = new ByteArrayInputStream(i);
		ByteArrayInputStream bais = null;
		SAXReader xmlReader = null;
		// ByteArrayInputStream in = new
		// ByteArrayInputStream(xmlContent.getBytes("KSC5601"));
		try {
			
			 xmlReader = new SAXReader();
			 bais = new ByteArrayInputStream(i);
			 headerDoc = xmlReader.read(bais);
			
		}catch (Exception e) {
			
			logger.debug("Exception");
			
		}finally {
			
			if(bais != null){
				bais.close();
			}
        }
	}

	public String[] getHeaderFieldNames() {
		String objects = "";
		List list = headerDoc.selectNodes("//QvxFieldHeader");
		for (int i = 0; list.size() > i; i++) {
			Element el = (Element) list.get(i);
			Element childObjects = el.element("FieldName");
			objects += childObjects.getText() + "" + ",";
		}

		if (objects.length() > 1) {
			
			objects = objects.substring(0, objects.length() - 1);
			return objects.split(",");
			
		} else{
			
			return null;
		}
	}
	
	public String getRecodeString(){
		dataString = "";
		this.readRecord();
		return dataString;
	}

	public String[] readRecord() {
		String as[] = new String[0];
		byte abyte0[] = new byte[4];
		byte abyte1[] = new byte[8];
		ByteBuffer bytebuffer = ByteBuffer.allocate(4);
		ByteBuffer bytebuffer1 = ByteBuffer.allocate(8);
		bytebuffer.order(ByteOrder.LITTLE_ENDIAN);
		bytebuffer1.order(ByteOrder.LITTLE_ENDIAN);
		NumberFormat numberformat = NumberFormat.getInstance();
		numberformat.setGroupingUsed(false);
	
		if (h) {
			f++;
			as = new String[g];
			int k = 0;
			boolean flag = false;
			try {
				do {
					int l;
					if ((l = a.read()) < 0) {
						break;
					}
					ArrayList arraylist = new ArrayList();
					String s = "";
					e++;
					switch (l) {
					case 30: // '\036'
						k = g;
						break;

					case 28: // '\034'
						h = false;
						k = g;
						break;

					case 0: // '\0'
						s = d;
						break;

					case 1: // '\001'
						a.read(abyte0);
						e += 4;
						bytebuffer.put(abyte0);
						bytebuffer.rewind();
						s = String.valueOf(bytebuffer.getInt());
						bytebuffer.clear();
						dataString +=s;
						break;

					case 2: // '\002'
						a.read(abyte1);
						e += 8;
						bytebuffer1.put(abyte1);
						bytebuffer1.rewind();
						s = numberformat.format(Double
								.longBitsToDouble(bytebuffer1.getLong()));
						bytebuffer1.clear();
						dataString +=s;
						break;

					case 4: // '\004'
						do {
							int i1;
							if ((i1 = a.read()) < 0) {
								break;
							}
							e++;
							if (i1 == 0) {
								break;
							}
							arraylist.add(new Byte((byte) i1));
						} while (true);
						s = convertUTFString(arraylist);
						dataString +=s;
						break;

					case 5: // '\005'
						a.read(abyte0);
						e += 4;
						do {
							int j1;
							if ((j1 = a.read()) < 0) {
								break;
							}
							e++;
							if (j1 == 0) {
								break;
							}
							arraylist.add(new Byte((byte) j1));
						} while (true);
						s = a(arraylist);
						dataString +=s;
						break;

					case 6: // '\006'
						a.read(abyte1);
						e += 8;
						do {
							int k1;
							if ((k1 = a.read()) < 0) {
								break;
							}
							e++;
							if (k1 == 0) {
								break;
							}
							arraylist.add(new Byte((byte) k1));
						} while (true);
						s = a(arraylist);
						dataString +=s;
						break;
					}
					if (k >= g) {
						break;
					}
					as[k] = s;
					k++;
				} while (true);
				if (f > 0x186a0L) {
					h = false;
				}
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
		return as;
	}

	private String convertUTFString(ArrayList arraylist) {
		int k = arraylist.size();
		byte abyte0[] = new byte[k];
		int l = 0;
		for (Iterator iterator = arraylist.iterator(); iterator.hasNext();) {
			byte byte0 = ((Byte) iterator.next()).byteValue();
			abyte0[l++] = byte0;
		}

		String s = "";
		try {
			s = new String(abyte0, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			s = "";
		}
		return s;
	}

	private String a(ArrayList arraylist) {
		int k = arraylist.size();
		byte abyte0[] = new byte[k];
		int l = 0;
		for (Iterator iterator = arraylist.iterator(); iterator.hasNext();) {
			byte byte0 = ((Byte) iterator.next()).byteValue();
			abyte0[l++] = byte0;
		}

		String s = "";
		s = new String(abyte0);
		return s;
	}
	
	public String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {      
            return "";
        }
    }

	public void close() throws IOException {
		
		
		a.close();
		b.close();
		
		
	}

	public boolean hasRecord() {
		return h;
	}

	public long getRecordNumber() {
		return f;
	}

	public long getColumnNumber() {
		

		
		return (long) g;
	}
}