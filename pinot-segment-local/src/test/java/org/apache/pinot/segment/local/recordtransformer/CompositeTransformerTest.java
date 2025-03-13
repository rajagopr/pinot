package org.apache.pinot.segment.local.recordtransformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.apache.pinot.spi.config.table.TableConfig;
import org.apache.pinot.spi.config.table.TableType;
import org.apache.pinot.spi.config.table.ingestion.EnrichmentConfig;
import org.apache.pinot.spi.config.table.ingestion.IngestionConfig;
import org.apache.pinot.spi.recordtransformer.RecordTransformer;
import org.apache.pinot.spi.utils.builder.TableConfigBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CompositeTransformerTest {
  @Test
  public void testGetDefaultPreEnrichers()
      throws JsonProcessingException {
    String tableName = "myTable_OFFLINE";
    IngestionConfig ingestionConfig = new IngestionConfig();
    EnrichmentConfig erc1 = new EnrichmentConfig("generateColumn",
        new ObjectMapper().readTree("{\"fieldToFunctionMap\": {}}"), true);
    EnrichmentConfig erc2 = new EnrichmentConfig("noOp",
        new ObjectMapper().readTree("{}"), false);
    List<EnrichmentConfig> enrichmentConfigs = Arrays.asList(erc1, erc2);
    ingestionConfig.setEnrichmentConfigs(enrichmentConfigs);
    TableConfig tableConfig = new TableConfigBuilder(TableType.OFFLINE).setTableName(tableName)
        .setIngestionConfig(ingestionConfig).build();
    List<RecordTransformer> recordTransformers = CompositeTransformer.getDefaultPreEnrichers(tableConfig, null);
    assertEquals(recordTransformers.size(), 1);
  }
}
