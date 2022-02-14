package com.silvia.desaingrafis.Materi;

public class ModelPertemuan  {

        private String id_pertemuan;
        private String id_materi;
        private String pertemuan;
        private String file;

    public ModelPertemuan(String id_pertemuan, String id_materi, String pertemuan, String file) {
        this.id_pertemuan = id_pertemuan;
        this.id_materi = id_materi;
        this.pertemuan = pertemuan;
        this.file = file;
    }

    public String getId_pertemuan() {
        return id_pertemuan;
    }

    public void setId_pertemuan(String id_pertemuan) {
        this.id_pertemuan = id_pertemuan;
    }

    public String getId_materi() {
        return id_materi;
    }

    public void setId_materi(String id_materi) {
        this.id_materi = id_materi;
    }

    public String getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(String pertemuan) {
        this.pertemuan = pertemuan;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
