package sitekick.map;

import java.util.ArrayList;
import java.util.List;
import sitekick.dao.CategoryDAO;
import sitekick.entity.Category;
import sitekick.entity.EntityContext;
import sitekick.entity.Site;

public class SiteMap {

    public Site map(sitekick.data.Site jaxbObject) {
        //TODO move to service and pass category as param
        EntityContext entityContext = EntityContext.newInstance();

        Category category = null;
        CategoryDAO categoryDAO = new CategoryDAO(entityContext.getEntityManager());
        if (jaxbObject.getCategory() != null) {
            entityContext.beginTransaction();
            category = categoryDAO.getFirstCategoryByName(jaxbObject.getCategory().getName());
            entityContext.commitTransaction();
            if (category == null) {
                category = new Category();
                category.setName(jaxbObject.getCategory().getName());
                entityContext.beginTransaction();
                categoryDAO.create(category);
                entityContext.commitTransaction();
            }
        }

        // end region
        Site site = new Site();
        site.setUrl(jaxbObject.getUrl());
        site.setCountry(jaxbObject.getCountry());
        site.setCountryRank(jaxbObject.getCountryRank());
        site.setGlobalRank(jaxbObject.getGlobalRank());

        site.setCategoryId(category);
        return site;
    }

    public List<Site> mapList(List<sitekick.data.Site> jaxbObjectList) {
        List<Site> result = new ArrayList();
        jaxbObjectList.forEach(x -> {
            result.add(map(x));
        });
        return result;
    }
}
