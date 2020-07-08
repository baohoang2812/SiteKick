package prx.map;

import org.modelmapper.PropertyMap;
import prx.data.Site;
import prx.entity.Category;


public class SiteMap extends PropertyMap<Site, prx.entity.Site> {
    @Override
    protected void configure() {
        //TODO find CATEGORY BY NAME
        map().setCategory(new Category());
    }
}
