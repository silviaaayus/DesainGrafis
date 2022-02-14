package com.silvia.desaingrafis.Materi;


public class ModelList  {

        private String id_materi;
        private String semester;
        private String nama_materi;

    public ModelList(String id_materi, String semester, String nama_materi) {
        this.id_materi = id_materi;
        this.semester = semester;
        this.nama_materi = nama_materi;
    }

    public String getId_materi() {
        return id_materi;
    }

    public void setId_materi(String id_materi) {
        this.id_materi = id_materi;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getNama_materi() {
        return nama_materi;
    }

    public void setNama_materi(String nama_materi) {
        this.nama_materi = nama_materi;
    }
}
