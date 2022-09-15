package org.openmetadata.apis.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;

public interface ElasticSearchIndex {
  public Map<String, Object> buildESDoc() throws JsonProcessingException;
}