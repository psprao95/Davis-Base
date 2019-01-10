package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.text.ParseException;
import java.io.File;
import java.time.ZoneId;

public class Utils {
	
	public static String getDatabasePath(String databaseName)
	{
		return DatabaseConstants.DEFAULT_DATA_DIRNAME+"/"+databaseName;
	}
	
	public static void printMessage(String str)
	{
		System.out.println(str);
	}
	
	public static void printMissingTableError(String databaseName,String tableName)
	{
		printMessage("Error(105T): Table '"+databaseName+"."+tableName+"'does not exist");
	}
	
	public static void printMissingDatabaseError(String databaseName)
	{
		printMessage("Error(104T): Database '"+databaseName+"'does not exist");
	}
	public static boolean isValidDateFormat(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		try
		{
			formatter.parse(date);
		}
		catch(ParseException e)
		{
			return false;
		}
		return true;
		
	}
	public static boolean isValidDateTimeFormat(String date)
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setLenient(false);
		try
		{
			formatter.parse(date);
		}
		catch(ParseException e)
		{
			return false;
		}
		return true;
	}
	
	
	public static long getDateEpoc(String value, boolean isDate)
	{
		DateFormat formatter;
		if(isDate)
		{
			formatter=new SimpleDateFormat("yyyy-MM-dd");
		}
		else
		{
			formatter=new SimpleDateFormat("yyyy-MM:-dd HH:mm:ss");
		}
		formatter.setLenient(false);
		Date date;
		try
		{
			date=formatter.parse(value);
			ZonedDateTime zdt= ZonedDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
			return zdt.toInstant().toEpochMilli()/1000;
		}
		catch(ParseException ex)
		{
			return 0;
		}
	}
	
	
	
	public static boolean RecursivelyDelete(File file)
	{
		if(file==null)
		{
			return true;
		}
		boolean isDeleted;
		if(file.isDirectory())
		{
			for(File childFile:file.listFiles())
			{
				if(childFile.isFile())
				{
				isDeleted=childFile.delete()	;
				if(!isDeleted)
				{
					return false;
				}
				}
				else
				{
					isDeleted = RecursivelyDelete(childFile);
					if(!isDeleted)
					{
						return false;
					}
					}
				}
			}
		return file.delete()
;		
	}

}
