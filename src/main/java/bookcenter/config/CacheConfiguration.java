package bookcenter.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(bookcenter.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(bookcenter.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(bookcenter.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(bookcenter.domain.Book.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.BookCenter.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.BookCenter.class.getName() + ".departments", jcacheConfiguration);
            cm.createCache(bookcenter.domain.BookCenter.class.getName() + ".employees", jcacheConfiguration);
            cm.createCache(bookcenter.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Department.class.getName() + ".employees", jcacheConfiguration);
            cm.createCache(bookcenter.domain.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Publisher.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.StockItem.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Warehouse.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.Warehouse.class.getName() + ".stockItems", jcacheConfiguration);
            cm.createCache(bookcenter.domain.PurchaseOrder.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.PurchaseOrder.class.getName() + ".orderItems", jcacheConfiguration);
            cm.createCache(bookcenter.domain.SalesOrder.class.getName(), jcacheConfiguration);
            cm.createCache(bookcenter.domain.SalesOrder.class.getName() + ".orderItems", jcacheConfiguration);
            cm.createCache(bookcenter.domain.OrderItem.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
