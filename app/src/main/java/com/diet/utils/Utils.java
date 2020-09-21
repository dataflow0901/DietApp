package com.diet.utils;


import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;


public class Utils {



    public static String getUserId(Context context){
        String userSY = "ysy";
        String userWC = "kwc";

        return userWC;
    }

    public static String setDecimalFormat(String input) {
        Log.d("Utils", "setDecimalFormat > input : " + input);
        DecimalFormat commas = new DecimalFormat("#,###");
        int output = Integer.parseInt(input);
        return commas.format(output);

    }



    public static String getCurrentURL(Context context) {

        String server = "http://112.217.209.162:9090";
        String SYURL = "http://192.168.0.8:9090";
        String JGURL = "http://192.168.0.3:9090";

        String SYURL2 = "http://192.168.219.102:9090";

        return  SYURL ;
    }


/*
    public static String firebaseAlert(CertificateDTO bill) {
//        val bill = CertificateDTO()

//        bill.loanNo = loanNo
//        bill.creditorName = creditorName
//        bill.debtorName = debtorName

        val res: Call<JsonObject> =
        //CertificateApiRetrofit.getInstance(this).service.confirmBill(bill)
        FirebaseApiRetrofit.getInstance(this).service.notify(bill)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("result", response.toString())
                val result = response.body()
                if (response.isSuccessful) {
                    Log.d("firebaseAlert", result.toString())
                    Toast.makeText(applicationContext,"fire base alarm message", Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    try {
                        val errorMessage = response.errorBody()!!.string()
                        Log.d("isSuccessful", errorMessage)
                    } catch (e: IOException) {
                        e.toString()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                t.message
            }
        })
    }
*/



//    public static String getTargetDir() {
//        return targetDir;
//    }
//
//    public static Date longToDate(long time) {
//        Date date = new Date();
//        date.setTime(time);
//        return date;
//    }
//
//    public static String dateToString(Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return sdf.format(date);
//    }
//
//    public static String timeToString(long time) {
//        return dateToString(longToDate(time));
//    }
//
//    public static String fileToImagePath(String file) {
//        return targetFile + File.separator + file;
//    }
//
//    public static String dateToyyyyMMdd(Date date) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.format(date.getTime());
//    }
//
//    public static File saveBitmapToJpeg(Context context, Bitmap bitmap, String name){
//        File storage = context.getCacheDir();
//        String fileName = name + ".jpg";
//        File tempFile = new File(storage, fileName);
//
//        try{
//            tempFile.createNewFile();
//
//            FileOutputStream out = new FileOutputStream(tempFile);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//            out.close();
//        } catch (FileNotFoundException e){
//            Log.e(TAG,e.getMessage());
//        } catch (IOException e){
//            Log.e(TAG,e.getMessage());
//        }
//
//        return tempFile;
//    }
//    public static String sha256(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] hash = digest.digest(msg.getBytes("UTF-8"));
//        StringBuffer hexString = new StringBuffer();
//
//        for (int i = 0; i < hash.length; i++) {
//            String hex = Integer.toHexString(0xff & hash[i]);
//            if (hex.length() == 1) hexString.append('0');
//            hexString.append(hex);
//        }
//        return hexString.toString();
//    }
}