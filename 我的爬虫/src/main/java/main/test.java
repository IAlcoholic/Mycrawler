package main;
import db.jdbc;
import model.jsmodel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class test {

    public static void main(String args[]) throws IOException {
        String address ="http://www.jianshu.com";
        jdbc jdbc = new jdbc();
        jsmodel data1 = new jsmodel();
        List<jsmodel> datas = new ArrayList<jsmodel>();
        Document doc = Jsoup.connect( "http://www.jianshu.com/" ).get();
        Element ul = doc.select("[class=note-list]").first();
        Elements all_li = ul.select( "li" ).nextAll();
        for (Element names  : all_li){
            String js_neme = names.select( "[class=blue-link]" ).text();
            String js_title = names.select( "[class=title]" ).text();
            String js_content = names.select( "[class=abstract]" ).text();
            String js_title_href = names.select( "[class=title]" ).attr( "href" );
            String address_content=address+js_title_href;
            Document doc1 = Jsoup.connect( address_content ).get();
            Element CONTENTS = doc1.select( "[class=show-content]" ).first();
            data1.setJs_content( CONTENTS.toString() );
            System.out.println(data1.getJs_content()+"----------------------------------------------------------------------------------");
            data1.setJs_name( js_neme );
            data1.setJs_title( js_title );
            datas.add( data1 );
            jdbc.insert( data1 );
        }
        jdbc.getAll();
    }
}
