package econtractapi.utility;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil
{
  public StringUtil() {}
  
  public static String Separator(String path)
  {
    StringBuilder sb = new StringBuilder();
    int ii = 0;
    while (ii < path.length())
    {
      char c = path.charAt(ii);
      sb.append(c);
      if (c == ':')
      {
        sb.append(File.separator);
        for (int i = ii + 1; i < path.length(); i++)
        {
          sb.append(path.charAt(i));
        }
        break;
      }
      ii++;
    }
    
    return sb.toString();
  }
  
  public static String SeparatorV2(String path) {
    StringBuilder sb = new StringBuilder();
    int ii = 0;
    while (ii < path.length())
    {
      char c = path.charAt(ii);
      sb.append(c);
      if (c == ':')
      {
        sb.append(File.separator);
        for (int i = ii + 2; i < path.length(); i++)
        {
          sb.append(path.charAt(i));
        }
        break;
      }
      ii++;
    }
    
    return sb.toString();
  }
  
  public static String encriptMD5(String str)
  {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes(), 0, str.length());
      String md5 = new BigInteger(1, md.digest()).toString(16);
      
      if (md5.length() == 31) {} return "0" + md5;
    }
    catch (NoSuchAlgorithmException e) {}
    return str;
  }
  

  public static int demKiTu(char c, String s)
  {
    int counter = 0;
    for (int i = 0; i < s.length(); i++)
    {
      if (s.charAt(i) == c)
      {
        counter++;
      }
    }
    return counter;
  }
  
  public static String VietHoa(String s) {
    String s1 = String.valueOf(s.charAt(0));
    s1 = s1.toUpperCase();
    int dem = 1;
    while (dem < s.length())
    {
      if (s.charAt(dem) != ' ')
      {
        s1 = s1 + String.valueOf(s.charAt(dem));
        dem++;
      }
      else
      {
        s1 = s1 + ' ';
        s1 = s1 + String.valueOf(s.charAt(dem + 1)).toUpperCase();
        dem += 2;
      }
    }
    return s1;
  }
  
  public static String XoaNDauCach(String s)
  {
    int vt3 = 0;
    while (vt3 < s.length())
    {
      if (s.charAt(vt3) == ' ')
      {
        int d = demDauCach(vt3, s);
        String s1 = s.substring(0, vt3);
        String s2 = s.substring(vt3 + d - 1, s.length());
        s = s1.concat(s2);
        vt3++;
      }
      else
      {
        vt3++;
      }
    }
    return s;
  }
  
  public static String XoaCachVietHoa(String s)
  {
    s = s.trim();
    s = XoaNDauCach(s);
    s = VietHoa(s);
    return s;
  }
  
  public static int demDauCach(int index, String s) {
    int dex = index;
    int dem = 0;
    while (s.charAt(dex) == ' ')
    {
      dem++;
      dex++;
    }
    return dem;
  }
  
  public static int checkStringAinB(String[] a, String[] b, int sa, int sb) {
    int dem = 0;
    for (int i = 0; i < sa; i++)
    {
      for (int j = 0; j < sb; j++)
      {
        if (a[i].compareTo(b[j]) == 0)
        {
          dem++;
        }
      }
    }
    if (dem == sa) return 1; return 0;
  }
  
  public static int checkStringAnotinB(String[] a, String[] b, int sa, int sb) {
    int dem = 0;
    for (int i = 0; i < sa; i++)
    {
      for (int j = 0; j < sb; j++)
      {
        if (a[i].compareTo(b[j]) == 0)
        {
          dem++;
        }
      }
    }
    if (dem == 0) return 1; return 0;
  }
  
  public static int lastNumberInString(String str)
  {
      int dem = 0;
      for(int i = str.length()-1;i>=0;i--)
      {
          if(Character.isDigit(str.charAt(i)))
          {
              dem = i;
              break;
          }
      }
      return dem;
  }
  
  public static String boDauCham(String pathAbs)
  {
      return pathAbs.substring(0, pathAbs.length()-2);
  }
  
  public static String percentBatch(String inpString)
  { 
      int vitri = 0;
      do
      {
          if(inpString.charAt(vitri)=='%')
          {
              if(vitri == 0)
              {
                  inpString = '%' + inpString;
                  vitri = vitri + 2;
              }
              else
              {
                  if(vitri == inpString.length()-1)
                  {
                      inpString = inpString + '%';
                      return inpString;
                  }
                  else
                  {
                      inpString = inpString.substring(0, vitri+1)+'%'+inpString.substring(vitri+1);
                      vitri = vitri + 2;
                  }
              }
          }
          else
          {
              vitri = vitri + 1;
          }
      }
      while(vitri<inpString.length());   
      return inpString;
  }
  public static String capnhatTF(String tf,String newX)
  {
      return tf.substring(0,tf.lastIndexOf('.')-1)+'.'+newX;
  }
    public static String SHA256_Hash(String str)
    {
        String hex = null;
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            hex = String.format("%064x", new BigInteger(1, digest));
        }
        catch (NoSuchAlgorithmException e) {}
        return hex;
    }
    public static String xlStringNull(String dauvao)
    {
        if(dauvao == null)
        {return "";}
        else
        {return dauvao;}
    }
    public static String xlNullString(String dauvao)
    {
        if("".equals(dauvao))
        {return null;}
        else
        {return dauvao;}
    }
    public static String catChuoi(String khoa1, String khoa2, String chuoi)
    {
        return chuoi.substring(chuoi.indexOf(khoa1)+khoa1.length(), chuoi.indexOf(khoa2));
    }
    
}
