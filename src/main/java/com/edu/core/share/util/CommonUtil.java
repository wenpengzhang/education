package com.edu.core.share.util;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class CommonUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 格式化日期
     * pattern如果为null则默认格式为yyyy-MM-dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 格式化日期
     * pattern如果为null则默认格式为yyyy-MM-dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Timestamp date, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 将String类型的日期格式化为Date类型
     * pattern如果为null则默认格式为yyyy-MM-dd HH:mm:ss
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String pattern) throws ParseException {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat formater = new SimpleDateFormat(pattern);
        return formater.parse(date);
    }

    /**
     * 格式化数字
     * @param num
     * @param value 保留的小数位
     * @return
     */
    public static String numberFormat(double num, int value) {
        NumberFormat formater = DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(value);
        formater.setGroupingUsed(false);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param str
     * @param value 保留的小数位
     * @return
     */
    public static String numberFormat(String str, int value) {
        double num = Double.parseDouble(str);
        NumberFormat formater = DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(value);
        formater.setGroupingUsed(false);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param str
     * @param formatStyle
     *            传入null时默认为0.00
     * @return
     */
    public static String numberFormat(String str, String formatStyle) {
        if (str == null || ("").equals(str)) {
            return "";
        }
        if (formatStyle == null) {
            formatStyle = "0.00";
        }
        Double num = Double.parseDouble(str);
        DecimalFormat formater = new DecimalFormat(formatStyle);
        formater.setRoundingMode(RoundingMode.HALF_UP);
        return formater.format(num);
    }

    /**
     * 格式化数字
     * @param num
     * @param formatStyle
     *            传入null时默认为0.00
     * @return
     */
    public static String numberFormat(double num, String formatStyle) {
        if (formatStyle == null) {
            formatStyle = "0.00";
        }
        DecimalFormat formater = new DecimalFormat(formatStyle);
        formater.setRoundingMode(RoundingMode.HALF_UP);
        return formater.format(num);
    }

    /**
     * util.Date转换为sql.Date
     * @param date
     * @return
     */
    public static java.sql.Date utilToSql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 返回传入的日期的前N天或后N天的日期
     * @param appDate
     * @param format
     * @param days
     * @return
     */
    public static String getFutureDay(String appDate, String format, int days) {
        String future = "";
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return future;
    }
    
    public static String getFutureMonth(String appDate, String format, int months) {
        String future = "";
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(appDate);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, months);
            date = calendar.getTime();
            future = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return future;
    }

    /**
     * 返回当前的时间戳
     * @add by lz
     * @return
     * @throws Exception
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 取得随机字符串
     * @param len
     *            长度
     * @param type
     *            类型 1:数字+字母混合 2:字母 3:数字
     * @return
     */
    public static String getRandomStr(int len, int type) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            if (type == 1) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                if (("char").equals(charOrNum)) {
                    str += (char) (choice + random.nextInt(26));
                } else if (("num").equals(charOrNum)) {
                    str += String.valueOf(random.nextInt(10));
                }
            } else if (type == 2) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                str += (char) (choice + random.nextInt(26));
            } else if (type == 3) {
                str += String.valueOf(random.nextInt(10));
            } else {
                str = "";
            }
        }
        return str;
    }

    /**
     * MethodName: getRandomStr </br>
     * Description: 根据长度和给定的字符数组生成随机字符串
     * @param len 字符串长度
     * @param charStr 字符数据组
     * @return
     */
    public static String getRandomStr(int len, String charStr) {
        String result = "";
        if (charStr == null) {
            return result;
        }
        int max = charStr.length();
        if (max == 0) {
            return result;
        }
        
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int choice = random.nextInt(max) % max;
            result += charStr.charAt(choice);
        }
        return result;
    }

    /**
     * @Title: replace
     * @Description: 将字符串的指定位置替换成相同的字符
     * @param @param str
     * @param @param 开始替换的位置
     * @param @param 结束替换的位置
     * @param @param 替换成的字符
     * @param @return
     * @param @throws Throwable 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String strReplace(String str, int startindex, int endindex, String newChar) throws Throwable {
        String s1 = "";
        String s2 = "";
        try {
            s1 = str.substring(0, startindex - 1);
            s2 = str.substring(endindex, str.length());
        } catch (Exception ex) {
            throw new Throwable("替换的位数大于字符串的位数");
        }
        Integer length = endindex - startindex;
        String charTmp = newChar;
        for (int i = 0; i < length; i++) {
            newChar += charTmp;
        }
        return s1 + newChar + s2;
    }

    /**
     * 检测密码强度
     * @param pwd
     * @return
     */
    public static int checkPwd(String pwd) {
        String regex = "^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$";
        return pwd.replaceAll(regex, "$1$2$3$4$5").length();
    }
    
    public static Map<String,Object> doParameters(HttpServletRequest request){
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,String[]> paramMap=request.getParameterMap();
		for(String key : paramMap.keySet()){
			String[] value=paramMap.get(key);
			if(value!=null && !"".equals(value[0])){
				param.put(key, StringUtils.join(value));
			}
		}
		return param;
	}
    
    public static String getCurrentYearAndMonth(){
    	Calendar calendar = Calendar.getInstance();
    	int year = calendar.get(Calendar.YEAR);
    	int month = calendar.get(Calendar.MONTH) + 1;//月份别忘了+1
    	
    	return year + "-" + (month < 10 ? "0"+ month : month); 
    	
    }
    
    public static String getNowMaxYM(){
        int nowyear=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int nowmonth=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int nowdate=Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        if(nowdate<20){
            if(nowmonth==1){
                return nowyear-1+"-"+11;
            }else if(nowmonth==2){
                return nowyear-1+"-"+12;
            }else{
                return nowyear+"-"+(nowmonth-2);
            }
        }else{
            if(nowmonth==1){
                return nowyear-1+""+12;
            }else{
                return nowyear+"-"+(nowmonth-1);
            }
        }
    }

    public static void main(String[] args) {
        try {
        	
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
