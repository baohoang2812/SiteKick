package prx.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prx.dao.CategoryDAO;
import prx.entity.Category;
import prx.entity.EntityContext;
import prx.entity.Site;

public class SiteMap {

    public Site map(prx.data.Site jaxbObject) {
        //TODO move to service and pass category as param
        EntityContext entityContext = EntityContext.newInstance();
        Category category = null;
        entityContext.beginTransaction();
        CategoryDAO categoryDAO = new CategoryDAO(entityContext.getEntityManager());
        category = categoryDAO.getFirstCategoryByName(jaxbObject.getCategory() != null ? jaxbObject.getCategory().getName() : null);
        entityContext.commitTransaction(); 
        // end region
        Site site = new Site();
        site.setUrl(jaxbObject.getUrl());
        site.setCountry(jaxbObject.getCountry());
        site.setCountryRank(jaxbObject.getCountryRank());
        site.setGlobalRank(jaxbObject.getGlobalRank());
        site.setCategoryId(category);
        return site;
    }
    
    public List<Site> mapList(List<prx.data.Site> jaxbObjectList) {
        List<Site> result = new ArrayList();
        jaxbObjectList.forEach(x -> {
            result.add(map(x));
        });
        return result;
    }
}
