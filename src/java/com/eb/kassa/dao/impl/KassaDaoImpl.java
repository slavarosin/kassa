package com.eb.kassa.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.InvoiceType;
import com.eb.kassa.beans.KassaItem;
import com.eb.kassa.beans.User;
import com.eb.kassa.dao.KassaDao;

@Repository
public class KassaDaoImpl extends AbstractDaoImpl<KassaItem> implements
		KassaDao {

	@Autowired
	public KassaDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, KassaItem.class);
	}

	public List<KassaItem> find(InvoiceType type, Currency currency) {
		return createCriteria().add(Restrictions.eq("type", type)).add(
				Restrictions.eq("currencyTo", currency)).list();
	}

	public List<KassaItem> find(InvoiceType type) {
		return createCriteria().add(Restrictions.eq("type", type)).list();
	}

	public List<String> findAllCreditors() {
		return createCriteria().add(Restrictions.isNotNull("creditor"))
				.setProjection(
						Projections.distinct(Projections.property("creditor")))
				.list();
	}

	public List<Currency> findAllCurrencies() {
		return createCriteria().add(Restrictions.isNotNull("currencyTo"))
				.setProjection(
						Projections
								.distinct(Projections.property("currencyTo")))
				.list();
	}

	public List<KassaItem> find(User user, String creditor, String comments,
			Date from, Date to, String orderBy, String orderType,
			InvoiceType... type) {
		Criteria criteria = createCriteria();
		if (type != null && type.length > 0) {
			criteria.add(Restrictions.in("type", type));
		}
		if (user != null) {
			criteria.add(Restrictions.eq("createdBy", user));
		}
		if (StringUtils.isNotEmpty(creditor)) {
			criteria.add(Restrictions.eq("creditor", creditor));
		}
		if (StringUtils.isNotEmpty(comments)) {
			criteria.add(Restrictions.eq("comments", comments));
		}
		if (from != null & to != null) {
			criteria.add(Restrictions.and(Restrictions.ge("date", from),
					Restrictions.le("date", to)));
		}

		Order order = null;
		if (StringUtils.isEmpty(orderBy))
			orderBy = "date";
		if (StringUtils.isEmpty(orderType) || orderType.equals("asc")) {
			order = Order.asc(orderBy);
		} else
			order = Order.desc(orderBy);

		return criteria.addOrder(order).list();
	}

	public List<KassaItem> find(InvoiceType type, User user, String creditor,
			String comments, Date from, Date to, Currency currency) {
		Criteria criteria = createCriteria();
		if (type != null) {
			criteria.add(Restrictions.eq("type", type));
		}
		if (user != null) {
			criteria.add(Restrictions.eq("createdBy", user));
		}
		if (currency != null) {
			criteria.add(Restrictions.eq("currencyTo", currency));
		}
		if (StringUtils.isNotEmpty(creditor)) {
			criteria.add(Restrictions.eq("creditor", creditor));
		}
		if (StringUtils.isNotEmpty(comments)) {
			criteria.add(Restrictions.eq("comments", comments));
		}
		if (from != null & to != null) {
			criteria.add(Restrictions.and(Restrictions.ge("date", from),
					Restrictions.le("date", to)));
		}

		return criteria.addOrder(Order.asc("date")).list();
	}

	public KassaItem findLastWithComment() {
		Integer id = (Integer) createCriteria().setProjection(
				Projections.max("id")).add(Restrictions.isNotNull("comments"))
				.uniqueResult();
		return find(id);
	}
}
