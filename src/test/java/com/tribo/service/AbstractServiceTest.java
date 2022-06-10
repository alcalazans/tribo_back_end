package com.tribo.service;

import com.tribo.TriboApplication;
import com.tribo.config.H2JpaConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SecurityConfiguration;

import static com.tribo.util.constants.DatabaseScripts.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TriboApplication.class, H2JpaConfig.class,
        SecurityConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql(scripts = { DROP_DDL, CREATE_DDL, INSERT_BASICOS},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
@ActiveProfiles("test")
public abstract class AbstractServiceTest {

    @Autowired
    protected RestTemplate restTemplate;

    protected MockRestServiceServer mockServer;

    @Before
    public void init() {

        this.mockServer = MockRestServiceServer.createServer(this.restTemplate);

    }

}
