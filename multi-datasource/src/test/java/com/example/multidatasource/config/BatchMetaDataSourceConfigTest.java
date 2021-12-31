package com.example.multidatasource.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        BatchMetaDataSourceConfig.class,
        BusinessMetaDataSourceConfig.class
})
class BatchMetaDataSourceConfigTest {

    @Autowired
    private ApplicationContext context;

    @DisplayName("basicDataSource 배치 연결 테스트")
    @Test
    void testCase1() throws SQLException {
        DataSource source = context.getBean("basicDataSource", DataSource.class);

        Connection connection = source.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        assertThat(metaData.getURL()).isEqualTo("jdbc:oracle:thin:@localhost:1524:XE");
    }

    @DisplayName("businessDataSource 비즈니스 연결 테스트")
    @Test
    void testCase2() throws SQLException {
        DataSource source = context.getBean("businessDataSource", DataSource.class);
        Connection connection = source.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        assertThat(metaData.getURL()).isEqualTo("jdbc:oracle:thin:@localhost:1525:XE");
    }
}