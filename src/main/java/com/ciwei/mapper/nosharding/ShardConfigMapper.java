package com.ciwei.mapper.nosharding;

import com.ciwei.model.ShardConfig;

import java.util.List;

public interface ShardConfigMapper {
    /**
     * selectByPrimaryKey
     * @param configKey
     * @return com.ciwei.model.ShardConfig
     */
    ShardConfig selectByPrimaryKey(String configKey);

    /**
     * selectAll
     * @param keys
     * @return java.util.List<com.ciwei.model.ShardConfig>
     */
    List<ShardConfig> selectByKey(List<String> keys);
}