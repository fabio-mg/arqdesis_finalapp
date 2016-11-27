package br.com.newidea.curso;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fabio on 27/11/16.
 */

public class ViewHolder {
    private int idCurso;
    private ImageView fotoCurso;
    private TextView nomeCurso, detalheCurso;

    public ViewHolder(int idCurso, ImageView fotoCurso, TextView nomeCurso, TextView detalheCurso) {
        this.fotoCurso = fotoCurso;
        this.nomeCurso = nomeCurso;
        this.detalheCurso = detalheCurso;
    }

    public int getIdCurso() { return idCurso; }

    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public ImageView getFotoCurso() {
        return fotoCurso;
    }

    public void setFotoCurso(ImageView fotoCurso) {
        this.fotoCurso = fotoCurso;
    }

    public TextView getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(TextView nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public TextView getDetalheCurso() {
        return detalheCurso;
    }

    public void setDetalheCurso(TextView detalheCurso) {
        this.detalheCurso = detalheCurso;
    }

}
