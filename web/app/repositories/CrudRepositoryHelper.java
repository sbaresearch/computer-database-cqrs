package repositories;

import models.Page;
import play.db.jpa.JPA;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CrudRepositoryHelper
{
    public static <T> Map<String,String> options(Class<T> tClass)
    {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

        // "from T order by name"
        CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.<T>createQuery(tClass);
        Root<T> root = query.from(tClass);
        query.select(root);
        query.orderBy(builder.asc(root.get("name")));

        List<T> entities = JPA.em().createQuery(query).getResultList();
        for(T e: entities)
        {
            String id = "", name = "";
            try
            {
                id = e.getClass().getField("id").get(e).toString();
                name = e.getClass().getField("name").get(e).toString();
            }
            catch (NoSuchFieldException | IllegalAccessException exception)
            {
                id = "";
                name = exception.getMessage();
            }
            options.put(id, name);
        }

        return options;
    }

    public static <T> Page<T> page(Class<T> tClass, int page, int pageSize, String sortBy, String order, String filter)
    {
        //TODO: Entity name may differ from class name, eg @Entity(name = "foobar")
        String entityName = tClass.getSimpleName();

        if(page < 1) page = 1;

        // TODO: Cache fields for performance?
        boolean validSortBy = false;
        Field[] fields = tClass.getFields();
        for(Field field : fields)
        {
            if(field.getName().equals(sortBy))
            {
                validSortBy = true;
            }
        }
        if(!validSortBy)
        {
            // throw new UnsupportedOperationException("Invalid sort key");

            // TODO: ID may be missing too
            sortBy = "id";
        }

        if(order != null && !order.equals("asc") && !order.equals("desc"))
        {
            order = "asc";
        }

        boolean useFilter = (filter != null && filter.trim().length()>0);

        String countSql = useFilter ?
                "select count(e) from " + entityName + " e where lower(e.name) like :filter" :
                "select count(e) from " + entityName + " e";
        Query countQuery = JPA.em().createQuery(countSql);
        if(useFilter)
        {
            countQuery.setParameter("filter", "%" + filter.toLowerCase() + "%");
        }
        Long total = (Long) countQuery.getSingleResult();

        String selectSql = useFilter ?
                "from " + entityName + " e where lower(e.name) like :filter order by e." + sortBy + " " + order :
                "from " + entityName + " e order by e." + sortBy + " " + order;
        Query selectQuery = JPA.em().createQuery(selectSql);
        if(useFilter)
        {
            selectQuery.setParameter("filter", "%" + filter.toLowerCase() + "%");
        }
        List<T> data = selectQuery
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return new Page(data, total, page, pageSize);
    }
}
