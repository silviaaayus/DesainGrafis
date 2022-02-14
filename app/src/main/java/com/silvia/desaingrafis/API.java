package com.silvia.desaingrafis;

public class API {

    private String URL ="https://sisfoagam.citragroup-hrd.com/api/desain/";

    public String MateriGanjil = URL+"listmateriganjil.php";
    public String MateriGenap = URL+"listmaterigenap.php";
    public String Pertemuan = URL+"pertemuan.php?id_materi=";
    public String IsiPertemuan = URL+"pdf/";
    public String EvaluasiGanjil = URL+"evaluasiganjil.php";
    public String EvaluasiGenap = URL+"evaluasigenap.php";
    public String Video = URL+"video.php";
}
