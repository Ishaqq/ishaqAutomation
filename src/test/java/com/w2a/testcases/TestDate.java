package com.w2a.testcases;


public class TestDate {
	 public static int monthNumber=11;
	    public static String monthName;
	    public String newMonthName;
	 

	    /**
	     * Constructor for objects of class Month
	     */
	
		 public static void main(String[] args)
		    {
		        switch(monthNumber){
		            case 01:
		                monthName = "January";
		                
		                break;
		            case 02:
		                monthName = "February";
		                break;
		            case 03:
		                monthName = "March";
		                break;
		            case 04:
		                monthName = "April";
		                break;
		            case 05:
		                monthName = "May";
		                break;
		            case 06:
		                monthName = "June";
		                break;
		            case 07:
		                monthName = "July";
		                break;
		            case 8:
		                monthName = "August";
		                break;
		            case 9:
		                monthName = "September";
		                break;
		            case 10:
		                monthName = "October";
		                break;
		            case 11:
		                monthName = "November";
		                break;
		            case 12:
		                monthName = "December";
		                break;
		            default:
		                monthName = "Invalid month";
		                break;
		        } 
		      
		        System.out.println(monthName);
		    }
		 

}
