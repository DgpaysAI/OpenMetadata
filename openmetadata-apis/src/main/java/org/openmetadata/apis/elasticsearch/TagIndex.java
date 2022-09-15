package org.openmetadata.apis.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openmetadata.apis.Entity;
import org.openmetadata.apis.util.JsonUtils;
import org.openmetadata.schema.entity.tags.Tag;

public class TagIndex implements ElasticSearchIndex {
  Tag tag;

  public TagIndex(Tag tag) {
    this.tag = tag;
  }

  public Map<String, Object> buildESDoc() throws JsonProcessingException {
    Map<String, Object> doc = JsonUtils.getMap(tag);
    List<ElasticSearchSuggest> suggest = new ArrayList<>();
    suggest.add(ElasticSearchSuggest.builder().input(tag.getFullyQualifiedName()).weight(5).build());
    suggest.add(ElasticSearchSuggest.builder().input(tag.getName()).weight(10).build());
    doc.put("suggest", suggest);
    doc.put("entityType", Entity.TAG);
    return doc;
  }
}