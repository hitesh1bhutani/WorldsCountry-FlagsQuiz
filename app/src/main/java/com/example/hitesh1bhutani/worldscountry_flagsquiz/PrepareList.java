package com.example.hitesh1bhutani.worldscountry_flagsquiz;

/**
 * Created by hitesh1bhutani on 17-01-2017.
 */
class PrepareList {
    static int[] countryNameList(int oceanFlag){
        int[] covers=null;
        if(oceanFlag==0) {
            covers=new int[]{
                    R.drawable.algeria, R.drawable.angola, R.drawable.burkina_faso,
                    R.drawable.burundi, R.drawable.cameroon, R.drawable.cape_verde,
                    R.drawable.central_african_republic, R.drawable.chad, R.drawable.comoros,
                    R.drawable.cote_d_ivoire, R.drawable.democratic_republic_of_congo, R.drawable.djibouti,
                    R.drawable.egypt, R.drawable.equatorial_guinea, R.drawable.eritrea,
                    R.drawable.ethiopia, R.drawable.gabon, R.drawable.gambia,
                    R.drawable.ghana, R.drawable.guinea, R.drawable.guinea_bissau,
                    R.drawable.kenya, R.drawable.lesotho, R.drawable.liberia,
                    R.drawable.libya, R.drawable.madagascar, R.drawable.malawi,
                    R.drawable.mali, R.drawable.mauritania, R.drawable.mauritius,
                    R.drawable.morocco, R.drawable.mozambique, R.drawable.namibia,
                    R.drawable.niger, R.drawable.nigeria, R.drawable.republic_of_the_congo,
                    R.drawable.rwanda, R.drawable.sao_tome_and_principe, R.drawable.senegal,
                    R.drawable.seychelles, R.drawable.sierra_leone, R.drawable.somalia,
                    R.drawable.south_sudan, R.drawable.south_africa, R.drawable.sudan, R.drawable.swaziland,
                    R.drawable.tanzania, R.drawable.togo, R.drawable.tunisia,
                    R.drawable.uganda, R.drawable.zambia, R.drawable.zimbabwe };
        }
        else if(oceanFlag==1) {
            covers= new int[]{
                    R.drawable.afghanistan, R.drawable.armenia,
                    R.drawable.azerbaijan, R.drawable.bahrain,
                    R.drawable.bangladesh, R.drawable.bhutan,
                    R.drawable.brunei, R.drawable.cambodia,
                    R.drawable.china, R.drawable.cyprus,
                    R.drawable.india, R.drawable.indonesia,
                    R.drawable.iran, R.drawable.iraq,
                    R.drawable.israel, R.drawable.japan,
                    R.drawable.jordan, R.drawable.kazakhstan,
                    R.drawable.kuwait, R.drawable.kyrgyzstan,
                    R.drawable.laos, R.drawable.lebanon,
                    R.drawable.malaysia, R.drawable.maldives,
                    R.drawable.mongolia, R.drawable.myanmar,
                    R.drawable.nepal, R.drawable.north_korea,
                    R.drawable.oman, R.drawable.pakistan,
                    R.drawable.philippines, R.drawable.qatar,
                    R.drawable.russia, R.drawable.saudi_arabia,
                    R.drawable.singapore, R.drawable.south_korea,
                    R.drawable.sri_lanka, R.drawable.syria,
                    R.drawable.taiwan, R.drawable.tajikistan,
                    R.drawable.thailand, R.drawable.turkey,
                    R.drawable.turkmenistan, R.drawable.united_arab_emirates,
                    R.drawable.vietnam, R.drawable.yemen
            };
        }
        else if(oceanFlag==2) {
            covers= new int[] {
                    R.drawable.albania, R.drawable.andorra,
                    R.drawable.austria, R.drawable.belarus,
                    R.drawable.belgium, R.drawable.bosnia_and_herzegovina,
                    R.drawable.botswana, R.drawable.bulgaria,
                    R.drawable.croatia, R.drawable.czech_republic,
                    R.drawable.denmark, R.drawable.estonia,
                    R.drawable.finland, R.drawable.france,
                    R.drawable.georgia, R.drawable.germany,
                    R.drawable.greece, R.drawable.hungary,
                    R.drawable.iceland, R.drawable.ireland,
                    R.drawable.italy, R.drawable.kosovo,
                    R.drawable.latvia, R.drawable.liechtenstein,
                    R.drawable.lithuania, R.drawable.luxembourg,
                    R.drawable.macedonia, R.drawable.malta,
                    R.drawable.moldova, R.drawable.monaco,
                    R.drawable.montenegro, R.drawable.netherlands,
                    R.drawable.norway, R.drawable.poland,
                    R.drawable.portugal, R.drawable.romania,
                    R.drawable.san_marino, R.drawable.serbia,
                    R.drawable.slovakia, R.drawable.slovenia,
                    R.drawable.spain, R.drawable.sweden,
                    R.drawable.switzerland, R.drawable.ukraine,
                    R.drawable.united_kingdom, R.drawable.vatican_city
            };
        }
        else if(oceanFlag==3) {
            covers= new int[]{
                    R.drawable.antigua_and_barbuda, R.drawable.bahamas,
                    R.drawable.barbados, R.drawable.belize,
                    R.drawable.canada, R.drawable.costa_rica,
                    R.drawable.cuba, R.drawable.dominica,
                    R.drawable.el_salvador, R.drawable.grenada,
                    R.drawable.guatemala, R.drawable.haiti,
                    R.drawable.honduras, R.drawable.jamaica,
                    R.drawable.newmexico, R.drawable.nicaragua,
                    R.drawable.saint_kitts_and_nevis, R.drawable.united_states
            };
        }
        else if(oceanFlag==4) {
            covers=new int[] {
                    R.drawable.australia, R.drawable.east_timor,
                    R.drawable.fiji, R.drawable.kiribati,
                    R.drawable.marshall_islands, R.drawable.micronesia,
                    R.drawable.nauru, R.drawable.new_zealand,
                    R.drawable.niue, R.drawable.palau,
                    R.drawable.papua_new_guinea, R.drawable.samoa,
                    R.drawable.solomon_islands, R.drawable.tonga,
                    R.drawable.tuvalu
            };
        }
        else if(oceanFlag==5) {
            covers= new int[] {
                    R.drawable.argentina, R.drawable.bolivia,
                    R.drawable.brazil, R.drawable.chile,
                    R.drawable.colombia, R.drawable.dominican_republic,
                    R.drawable.ecuador, R.drawable.guyana,
                    R.drawable.panama, R.drawable.paraguay,
                    R.drawable.peru, R.drawable.saint_lucia,
                    R.drawable.saint_vincent_and_the_grenadines, R.drawable.suriname,
                    R.drawable.trinidad_and_tobago, R.drawable.uruguay,
                    R.drawable.venezuela
            };
        }
        return covers;
    }

    static int[] countryNameListMain(int oceanFlag){
        int[] covers=null;
        if(oceanFlag==0) {
            covers=new int[]{
                    R.drawable.algeria_main, R.drawable.angola_main, R.drawable.burkina_faso_main,
                    R.drawable.burundi_main, R.drawable.cameroon_main, R.drawable.cape_verde_main,
                    R.drawable.central_african_republic_main, R.drawable.chad_main, R.drawable.comoros_main,
                    R.drawable.cote_d_ivoire_main, R.drawable.democratic_republic_of_congo_main, R.drawable.djiboti_main,
                    R.drawable.egypt_main, R.drawable.equatorial_guinea_main, R.drawable.eritrea_main,
                    R.drawable.ethiopia_main, R.drawable.gabon_main, R.drawable.gambia_main,
                    R.drawable.ghana_main, R.drawable.guinea_main, R.drawable.guinea_bissau_main,
                    R.drawable.kenya_main, R.drawable.lesotho_main, R.drawable.liberia_main,
                    R.drawable.libya_main, R.drawable.madagascar_main, R.drawable.malawi_main,
                    R.drawable.mali_main, R.drawable.mauritania_main, R.drawable.mauritius_main,
                    R.drawable.morocco_main, R.drawable.mozambique_main, R.drawable.namibia_main,
                    R.drawable.niger_main, R.drawable.nigeria_main, R.drawable.republic_of_the_congo_main,
                    R.drawable.rwanda_main, R.drawable.sao_tome_and_principe_main, R.drawable.senegal_main,
                    R.drawable.seychelles_main, R.drawable.sierra_leone_main, R.drawable.somalia_main,
                    R.drawable.south_sudan_main, R.drawable.south_africa_main, R.drawable.sudan_main, R.drawable.swaziland_main,
                    R.drawable.tanzania_main, R.drawable.togo_main, R.drawable.tunisia_main,
                    R.drawable.uganda_main, R.drawable.zambia_main, R.drawable.zimbabwe_main };
        }
        else if(oceanFlag==1) {
            covers= new int[]{
                    R.drawable.afghanistan_main, R.drawable.armenia_main,
                    R.drawable.azerbaijan_main, R.drawable.bahrain_main,
                    R.drawable.bangladesh_main, R.drawable.bhutan_main,
                    R.drawable.brunei_main, R.drawable.cambodia_main,
                    R.drawable.china_main, R.drawable.cyprus_main,
                    R.drawable.india_main, R.drawable.indonesia_main,
                    R.drawable.iran_main, R.drawable.iraq_main,
                    R.drawable.israel_main, R.drawable.japan_main,
                    R.drawable.jordan_main, R.drawable.kazakhstan_main,
                    R.drawable.kuwait_main, R.drawable.kyrgyzstan_main,
                    R.drawable.laos_main, R.drawable.lebanon_main,
                    R.drawable.malaysia_main, R.drawable.maldives_main,
                    R.drawable.mongolia_main, R.drawable.myanmar_main,
                    R.drawable.nepal_main, R.drawable.north_korea_main,
                    R.drawable.oman_main, R.drawable.pakistan_main,
                    R.drawable.philippines_main, R.drawable.qatar_main,
                    R.drawable.russia_main, R.drawable.saudi_arabia_main,
                    R.drawable.singapore_main, R.drawable.south_korea_main,
                    R.drawable.sri_lanka_main, R.drawable.syria_main,
                    R.drawable.taiwan_main, R.drawable.tajikistan_main,
                    R.drawable.thailand_main, R.drawable.turkey_main,
                    R.drawable.turkmenistan_main, R.drawable.united_arab_emirates_main,
                    R.drawable.vietnam_main, R.drawable.yemen_main
            };
        }
        else if(oceanFlag==2) {
            covers= new int[] {
                    R.drawable.albania_main, R.drawable.andorra_main,
                    R.drawable.austria_main, R.drawable.belarus_main,
                    R.drawable.belgium_main, R.drawable.bosnia_and_herzegovina_main,
                    R.drawable.botswana_main, R.drawable.bulgaria_main,
                    R.drawable.croatia_main, R.drawable.czech_republic_main,
                    R.drawable.denmark_main, R.drawable.estonia_main,
                    R.drawable.finland_main, R.drawable.france_main,
                    R.drawable.georgia_main, R.drawable.germany_main,
                    R.drawable.greece_main, R.drawable.hungary_main,
                    R.drawable.iceland_main, R.drawable.ireland_main,
                    R.drawable.italy_main, R.drawable.kosovo_main,
                    R.drawable.latvia_main, R.drawable.liechtenstein_main,
                    R.drawable.lithuania_main, R.drawable.luxembourg_main,
                    R.drawable.macedonia_main, R.drawable.malta_main,
                    R.drawable.moldova_main, R.drawable.monaco_main,
                    R.drawable.montenegro_main, R.drawable.netherlands_main,
                    R.drawable.norway_main, R.drawable.poland_main,
                    R.drawable.portugal_main, R.drawable.romania_main,
                    R.drawable.san_marino_main, R.drawable.serbia_main,
                    R.drawable.slovakia_main, R.drawable.slovenia_main,
                    R.drawable.spain_main, R.drawable.sweden_main,
                    R.drawable.switzerland_main, R.drawable.ukraine_main,
                    R.drawable.united_kingdom_main, R.drawable.vatican_city_main
            };
        }
        else if(oceanFlag==3) {
            covers= new int[]{
                    R.drawable.antigua_and_barbuda_main, R.drawable.bahamas_main,
                    R.drawable.barbados_main, R.drawable.belize_main,
                    R.drawable.canada_main, R.drawable.costa_rica_main,
                    R.drawable.cuba_main, R.drawable.dominica_main,
                    R.drawable.el_salvador_main, R.drawable.greneda_main,
                    R.drawable.guatemala_main, R.drawable.haiti_main,
                    R.drawable.honduras_main, R.drawable.jamaica_main,
                    R.drawable.mexico_main, R.drawable.nicaragua_main,
                    R.drawable.saint_kitts_and_nevis_main, R.drawable.united_states_of_america_main
            };
        }
        else if(oceanFlag==4) {
            covers=new int[] {
                    R.drawable.australia_main, R.drawable.east_timor_main,
                    R.drawable.fiji_main, R.drawable.kiribati_main,
                    R.drawable.marshall_islands_main, R.drawable.micronesia_main,
                    R.drawable.nauru_main, R.drawable.new_zealand_main,
                    R.drawable.niue_main, R.drawable.palau_main,
                    R.drawable.papua_new_guinea_main, R.drawable.samoa_main,
                    R.drawable.solomon_islands_main, R.drawable.tonga_main,
                    R.drawable.tuvalu_main
            };
        }
        else if(oceanFlag==5) {
            covers= new int[] {
                    R.drawable.argentina_main, R.drawable.bolivia_main,
                    R.drawable.brazil_main, R.drawable.chile_main,
                    R.drawable.colombia_main, R.drawable.dominican_republic_main,
                    R.drawable.ecuador_main, R.drawable.guyana_main,
                    R.drawable.panama_main, R.drawable.paraguay_main,
                    R.drawable.peru_main, R.drawable.saint_lucia_main,
                    R.drawable.saint_vincent_and_the_grenadines_main, R.drawable.suriname_main,
                    R.drawable.trinidad_and_tobago_main, R.drawable.uruguay_main,
                    R.drawable.venezuela_main
            };
        }
        return covers;
    }
}
