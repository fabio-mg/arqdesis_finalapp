package br.com.newidea.curso;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.io.IOException;
import java.util.Hashtable;


/**
 * Created by fabio on 27/11/16.
 */

public class CursoAdapter extends BaseAdapter implements SectionIndexer {
    Activity context;
    Curso[] cursos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;
    ViewHolder holder;


    public CursoAdapter(Activity context, Curso[] cursos){
        this.context = context;
        this.cursos = cursos;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(cursos);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(cursos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(cursos);
    }
    
    @Override
    public int getCount() {
        return cursos.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < cursos.length)
            return cursos[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;

        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_curso, parent, false);

            ImageView fotoCurso = (ImageView) view.findViewById(R.id.foto_curso);
            TextView nomeCurso = (TextView) view.findViewById(R.id.nome_curso);
            TextView detalheCurso = (TextView) view.findViewById(R.id.detalhe_curso);

            //faz cache dos widgets instanciados na tag da view para reusar
            //quando houver reciclagem
            view.setTag(new ViewHolder(0, fotoCurso, nomeCurso, detalheCurso));
        }

        //usa os widgets cacheados na view reciclada
        holder = (ViewHolder) view.getTag();
        holder.setIdCurso(cursos[position].getIdCurso());
        holder.getNomeCurso().setText(cursos[position].getNome());
        holder.getDetalheCurso().setText(String.format("%s", cursos[position].getDetalhe() ));


        return view;
    }


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }

}
