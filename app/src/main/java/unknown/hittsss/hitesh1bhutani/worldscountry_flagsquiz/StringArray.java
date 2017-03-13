package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

/**
 * Created by hitesh1bhutani on 06-02-2017.
 */

class StringArray {
    static int getStringArrayFromResources(int position){
        if(position==0){
            return R.array.africa_flag_list;
        } else if(position==1){
            return R.array.asia_flag_list;
        } else if(position==2){
            return R.array.europe_flag_list;
        } else if(position==3){
            return R.array.north_america_flag_list;
        } else if(position==4){
            return R.array.oceania_flag_list;
        } else return R.array.south_america_flag_list;
    }
}
