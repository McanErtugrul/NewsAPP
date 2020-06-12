package com.example.mehme.haberappk;

import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;

import org.ksoap2.transport.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class ServiceManager {

    private String Haber_Baslik="Haber_Baslik";
        public volatile boolean parsingComplete=true;
    public String getHaber_Baslik() {
        return Haber_Baslik;
    }

    private static final String METHOD_NAME = "GetHaber";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/GetHaber";
       // private static final String URL = "http://192.168.1.107/Web/Servis.asmx";
       private static final String URL = "http://192.168.1.107/Xml/WebService.asmx";

    SoapObject soapObject;
    SoapSerializationEnvelope soapSerializationEnvelope;
    HttpTransportSE httpTransportSE;

    public void BegenmeUpdate(String numara, String veri) {

        soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
        soapObject.addProperty("ID", numara);
        soapObject.addProperty("begenme", veri);


        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapObject soapPrimitive = (SoapObject) soapSerializationEnvelope.getResponse();
            System.out.println(soapPrimitive.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void HaberListesi(String filtre) {

        String METHOD_NAME = "GetHaber";
        soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
        soapObject.addProperty("filtre", filtre);


        soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);

        httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.debug = true;
        try {
            httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
            SoapObject soapPrimitive = (SoapObject) soapSerializationEnvelope.getResponse();


            Log.d("ServiceManger",""+soapPrimitive.toString());

            SoapObject temp = (SoapObject) soapPrimitive.getProperty(1);

            for(int i=0; i<temp.getPropertyCount(); i++)
            {
                SoapObject obj3 =(SoapObject) temp.getProperty(i);
                String id= obj3.getPrimitivePropertyAsString("Id");
                String Haber_Baslik = obj3.getPrimitivePropertyAsString("Haber_Baslik");
                String Haber_Tur = obj3.getPrimitivePropertyAsString("Haber_Tur");
//                String title=obj3.getProperty("Haber_Baslik").toString();
                Log.d("Result ::: ", id + " --- " + Haber_Baslik+ " ---- " + Haber_Tur );
               // parseStringXml(id);
              //  Log.e("ServiceManger",""+id+title);

            }

        }catch(Exception ex){
                ex.printStackTrace();
            }
        }


        public void parseStringXml(XmlPullParser myParser){
        int event;
        String text=null;


        try{
            event=myParser.getEventType();
            while(event!=XmlPullParser.END_DOCUMENT){
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                    break;
                    case XmlPullParser.TEXT:
                        text=myParser.getText();
                    case XmlPullParser.END_TAG:
                        if (name.equals("Haber_Baslik")) {
                        Haber_Baslik=text;
                        }
                        else {

                        }
                        break;
                }
                event=myParser.next();
            }
            parsingComplete=false;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }





}
