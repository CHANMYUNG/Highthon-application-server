package com.highthon.highthon3server.service.application;

import com.highthon.highthon3server.domain.application.*;
import com.highthon.highthon3server.dto.application.ApplicationSaveDto;
import com.highthon.highthon3server.dto.application.ApplicationConditionDto;
import com.highthon.highthon3server.dto.application.SaveResponse;
import com.highthon.highthon3server.exception.ApplicationNotFoundException;
import com.highthon.highthon3server.exception.AuthenticationException;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EntityManager em;

    @Value("${limit.life-develop}")
    private Integer LIFE_DEVELOP_LIMIT;

    @Value("${limit.life-design}")
    private Integer LIFE_DESIGN_LIMIT;

    @Value("${limit.game-design}")
    private Integer GAME_DEVELOP_LIMIT;

    @Value("${limit.game-design}")
    private Integer GAME_DESIGN_LIMIT;

    List<String> sortableFeilds = Arrays.asList("applicationId", "name", "email", "sex", "area", "position", "phone", "belong", "createdDate");

    @Transactional
    public SaveResponse saveApplication(ApplicationSaveDto dto) {
        SaveResponse response = null;
        Integer waitingNumber = null;
        if (applicationRepository.existsByPhone(dto.getPhone()))
            throw new DuplicatedValueException("phone");
        if (applicationRepository.existsByEmail(dto.getEmail()))
            throw new DuplicatedValueException("email");

        int limit = getLimit(dto.getArea(), dto.getPosition());
        int count = applicationRepository.countByAreaAndPosition(dto.getArea(), dto.getPosition());

        Application application = dto.toEntity();
        application.setPassword(passwordEncoder.encode(application.getPassword()));
        application.setBelong(application.getBelong().replace(" ", ""));

        if (count < limit) {
            application.setIsAccepted(true);
        } else {
            application.setIsAccepted(false);
            waitingNumber = applicationRepository.getWaitingNumber(application);
        }

        applicationRepository.save(application);
        response = new SaveResponse(true, waitingNumber);

        return response;
    }

    private int getLimit(Area area, Position position) {
        if (area == Area.GAME) {
            if (position == Position.DESIGN) return GAME_DESIGN_LIMIT;
            else return GAME_DEVELOP_LIMIT;
        } else {
            if (position == Position.DESIGN) return LIFE_DESIGN_LIMIT;
            else return LIFE_DEVELOP_LIMIT;
        }
    }

    @Transactional
    public ApplicationCondition getApplicationCondition(ApplicationConditionDto dto) {
        Application application = applicationRepository.findByEmail(dto.getEmail()).orElse(null);
        if (application == null) throw new ApplicationNotFoundException();
        if (!passwordEncoder.matches(dto.getPassword(), application.getPassword()))
            throw new AuthenticationException("password does not match");

        ApplicationCondition condition = applicationRepository.getApplicationConditionById(application.getApplicationId());
        if (condition == null) throw new ApplicationNotFoundException();
        else return condition;
    }

    public List<Application> getAcceptedApplications(Pageable pageable) {
        return applicationRepository.getAcceptedApplications(pageable).getContent();
    }

    public List<ApplicationIncludesWaitingNumber> getWaitingApplications(@PageableDefault(sort = "applicationId,desc") Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ApplicationIncludesWaitingNumber> q = cb.createQuery(ApplicationIncludesWaitingNumber.class);
        Root<Application> root = q.from(Application.class);

        List<Order> orders = new ArrayList<>();
        pageable.getSort().stream().forEach(order -> {
            if (sortableFeilds.contains(order.getProperty())) {
                if (order.isAscending()) orders.add((cb.asc(root.get(order.getProperty()))));
                if (order.isDescending()) orders.add(cb.desc(root.get(order.getProperty())));
            }
        });

        Subquery<Long> sq = q.subquery(Long.class);
        Root<Application> subRoot = sq.from(Application.class);

        sq.select(cb.count(subRoot.get("applicationId")));
        sq.where(cb.and(
                cb.isFalse(subRoot.get("isAccepted")),
                cb.lessThanOrEqualTo(subRoot.get("createdDate"), root.get("createdDate")),
                cb.equal(subRoot.get("area"), root.get("area")),
                cb.equal(subRoot.get("position"), root.get("position"))));

        q.select(cb.construct(ApplicationIncludesWaitingNumber.class,
                root.get("applicationId"),
                root.get("name"),
                root.get("email"),
                root.get("sex"),
                root.get("area"),
                root.get("position"),
                root.get("phone"),
                root.get("belong"),
                root.get("isAccepted"),
                root.get("createdDate"),
                sq.getSelection()))
                .where(cb.isFalse(root.get("isAccepted")))
                .orderBy(orders);

        TypedQuery<ApplicationIncludesWaitingNumber> query = em.createQuery(q);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }
}
