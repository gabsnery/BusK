package com.example.gabi.busk_13_09;

import com.example.gabi.busk_13_09.POJO.ObjHorario;
import com.example.gabi.busk_13_09.POJO.ObjOnibus;
import com.example.gabi.busk_13_09.POJO.ObjPonto;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

//mport retrofit2.http.Field;

//import retrofit2.http.POST;


/**
 * Created by gabi on 01/09/2016.
 */

  //  private static final String ENDPOINT_URL = "http://192.168.43.154";
    //private static final String ENDPOINT_URL = "http://192.168.25.232";

    //private static OnibusService onibusService;
    public interface OnibusAPI
    {

        @GET("/retornaListaOnibus.php")
        void getBuses(Callback<List<ObjOnibus>> response);



        @FormUrlEncoded
        @POST("/retornaListaHorarios.php")
        void retornaHorarios(@Field("json") String json, Callback<List<ObjHorario>> callBack);

        @FormUrlEncoded
        @POST("/retornaOnibus.php")
        void selectOnibus(@Field("json") String json, Callback<ObjOnibus> callBack);

        @FormUrlEncoded
        @POST("/retornaListaPontos.php")
        void retornaPontos(@Field("json") String json, Callback<List<ObjPonto>> callBack);

    }

