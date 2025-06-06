/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.common.metrics;

import org.apache.pinot.common.Utils;


/**
 * Controller gauges.
 */
public enum ControllerGauge implements AbstractMetrics.Gauge {
  VERSION("version", true),

  REPLICATION_FROM_CONFIG("replicas", false),
  // Number of complete replicas of table in external view containing all segments online in ideal state
  NUMBER_OF_REPLICAS("replicas", false),

  // Percentage of complete online replicas in external view as compared to replicas in ideal state
  PERCENT_OF_REPLICAS("percent", false),

  SEGMENTS_IN_ERROR_STATE("segments", false),
  // Segment start and end time is stored in milliseconds.
  // Invalid start/end time means the broker time pruner will not work correctly.
  // Invalid end times means time retention will not happen for that segment.
  SEGMENTS_WITH_INVALID_START_TIME("segments", false),
  SEGMENTS_WITH_INVALID_END_TIME("segments", false),

  // Percentage of segments with at least one online replica in external view as compared to total number of segments in
  // ideal state
  PERCENT_SEGMENTS_AVAILABLE("segments", false),

  // Number of segments running with less than expected replicas in external view
  SEGMENTS_WITH_LESS_REPLICAS("segments", false),

  SEGMENT_COUNT("SegmentCount", false),

  // Number of segments including the replaced segments which are specified in the segment lineage entries and cannot
  // be queried from the table.
  SEGMENT_COUNT_INCLUDING_REPLACED("SegmentCount", false),

  // Track response size of getChildren from /PROPERTYSTORE/SEGMENTS/<table>
  PROPERTYSTORE_SEGMENT_CHILDREN_BYTE_SIZE("propertystore", false),
  IDEALSTATE_ZNODE_SIZE("idealstate", false),
  IDEALSTATE_ZNODE_BYTE_SIZE("idealstate", false),
  EXTERNALVIEW_ZNODE_SIZE("externalview", false),
  EXTERNALVIEW_ZNODE_BYTE_SIZE("externalview", false),
  REALTIME_TABLE_COUNT("TableCount", true),
  OFFLINE_TABLE_COUNT("TableCount", true),
  DISABLED_TABLE_COUNT("TableCount", true),
  UPSERT_TABLE_COUNT("TableCount", true),
  PERIODIC_TASK_NUM_TABLES_PROCESSED("PeriodicTaskNumTablesProcessed", true),
  TIME_MS_SINCE_LAST_MINION_TASK_METADATA_UPDATE("TimeMsSinceLastMinionTaskMetadataUpdate", false),
  TIME_MS_SINCE_LAST_SUCCESSFUL_MINION_TASK_GENERATION("TimeMsSinceLastSuccessfulMinionTaskGeneration", false),
  LAST_MINION_TASK_GENERATION_ENCOUNTERS_ERROR("LastMinionTaskGenerationEncountersError", false),
  // TODO: Unify below subtask metrics into a single metric with status label
  NUM_MINION_TASKS_IN_PROGRESS("NumMinionTasksInProgress", true),
  NUM_MINION_SUBTASKS_WAITING("NumMinionSubtasksWaiting", true),
  NUM_MINION_SUBTASKS_RUNNING("NumMinionSubtasksRunning", true),
  NUM_MINION_SUBTASKS_ERROR("NumMinionSubtasksError", true),
  NUM_MINION_SUBTASKS_UNKNOWN("NumMinionSubtasksUnknown", true),
  NUM_MINION_SUBTASKS_DROPPED("NumMinionSubtasksDropped", true),
  NUM_MINION_SUBTASKS_TIMED_OUT("NumMinionSubtasksTimedOut", true),
  NUM_MINION_SUBTASKS_ABORTED("NumMinionSubtasksAborted", true),
  PERCENT_MINION_SUBTASKS_IN_QUEUE("PercentMinionSubtasksInQueue", true),
  PERCENT_MINION_SUBTASKS_IN_ERROR("PercentMinionSubtasksInError", true),
  TIER_BACKEND_TABLE_COUNT("TierBackendTableCount", true),

  // Pinot controller leader
  PINOT_CONTROLLER_LEADER("PinotControllerLeader", true),

  // Pinot controller resource enabled
  PINOT_LEAD_CONTROLLER_RESOURCE_ENABLED("PinotLeadControllerResourceEnabled", true),

  // Number of partitions for which current controller becomes the leader
  CONTROLLER_LEADER_PARTITION_COUNT("ControllerLeaderPartitionCount", true),

  // Estimated size of offline table
  @Deprecated // Instead use TABLE_TOTAL_SIZE_ON_SERVER
  OFFLINE_TABLE_ESTIMATED_SIZE("OfflineTableEstimatedSize", false),

  LARGEST_SEGMENT_SIZE_ON_SERVER("LargestSegmentSizeOnServer", false),

  // Total size of table across replicas on servers
  TABLE_TOTAL_SIZE_ON_SERVER("TableTotalSizeOnServer", false),

  // Size of table per replica on servers
  TABLE_SIZE_PER_REPLICA_ON_SERVER("TableSizePerReplicaOnServer", false),

  // Total size of compressed segments per table
  TABLE_COMPRESSED_SIZE("TableCompressedSize", false),

  // Table quota based on setting in table config
  TABLE_QUOTA("TableQuotaBasedOnTableConfig", false),

  // Table storage quota utilization
  TABLE_STORAGE_QUOTA_UTILIZATION("TableStorageQuotaUtilization", false),

  // Percentage of segments we failed to get size for
  TABLE_STORAGE_EST_MISSING_SEGMENT_PERCENT("TableStorageEstMissingSegmentPercent", false),

  // Number of scheduled Cron jobs
  CRON_SCHEDULER_JOB_SCHEDULED("cronSchedulerJobScheduled", false),

  // Number of Tasks Status
  TASK_STATUS("taskStatus", false),

  // Number of dropped stale minion instances
  DROPPED_MINION_INSTANCES("droppedMinionInstances", true),

  // Number of dropped stale broker instances
  DROPPED_BROKER_INSTANCES("droppedBrokerInstances", true),

  // Number of dropped stale server instances
  DROPPED_SERVER_INSTANCES("droppedServerInstances", true),

  // Number of online minion instances
  ONLINE_MINION_INSTANCES("onlineMinionInstances", true),

  // Number of partitions with missing consuming segments in ideal state
  MISSING_CONSUMING_SEGMENT_TOTAL_COUNT("missingConsumingSegmentTotalCount", false),

  // Number of new partitions with missing consuming segments in ideal state
  MISSING_CONSUMING_SEGMENT_NEW_PARTITION_COUNT("missingConsumingSegmentNewPartitionCount", false),

  // Maximum duration of a missing consuming segment in ideal state (in minutes)
  MISSING_CONSUMING_SEGMENT_MAX_DURATION_MINUTES("missingSegmentsMaxDurationInMinutes", false),

  // Number of in progress segment downloads
  SEGMENT_DOWNLOADS_IN_PROGRESS("segmentDownloadsInProgress", true),

  // Number of in progress segment uploads
  SEGMENT_UPLOADS_IN_PROGRESS("segmentUploadsInProgress", true),

  // Records lag at a partition level
  MAX_RECORDS_LAG("maxRecordsLag", false),

  // Consumption availability lag in ms at a partition level
  MAX_RECORD_AVAILABILITY_LAG_MS("maxRecordAvailabilityLagMs", false),

  // Number of table without table config
  TABLE_WITHOUT_TABLE_CONFIG_COUNT("tableWithoutTableConfigCount", true),

  // Number of table with table config but without schema
  TABLE_WITHOUT_SCHEMA_COUNT("tableWithoutSchemaCount", true),

  LLC_SEGMENTS_DEEP_STORE_UPLOAD_RETRY_QUEUE_SIZE("LLCSegmentDeepStoreUploadRetryQueueSize", false),

  TABLE_CONSUMPTION_PAUSED("tableConsumptionPaused", false),

  TABLE_DISABLED("tableDisabled", false),

  // A per-table metric that shows the number of rows we expect to consume for the next segment of
  // any partition in the realtime table. This metric is emitted from the segment size based threshold
  // computer.
  NUM_ROWS_THRESHOLD("numRowsThreshold", false),
  // Added to preserve backwards compatibility of the above metric
  NUM_ROWS_THRESHOLD_WITH_TOPIC("numRowsThresholdWithTopic", false),

  // The actual segment size for committing segments. These may be shorter than expected when the administrator
  // issues a force-commit, or zero when new partitions are detected in the stream (since there is no completing
  // segment when the partition is first detected).
  COMMITTING_SEGMENT_SIZE("committingSegmentSize", false),
  // Added to preserve backwards compatibility of the above metric
  COMMITTING_SEGMENT_SIZE_WITH_TOPIC("committingSegmentSizeWithTopic", false),

  TABLE_REBALANCE_IN_PROGRESS("tableRebalanceInProgress", false),

  TABLE_REBALANCE_IN_PROGRESS_GLOBAL("jobs", true),

  // Number of reingested segments getting uploaded
  REINGESTED_SEGMENT_UPLOADS_IN_PROGRESS("reingestedSegmentUploadsInProgress", true),

  // Resource utilization is within limits or not for a table
  RESOURCE_UTILIZATION_LIMIT_EXCEEDED("ResourceUtilizationLimitExceeded", false),

  // The number of segments in deepstore that do not have corresponding metadata in ZooKeeper.
  // These segments are untracked and should be considered for deletion based on retention policies.
  UNTRACKED_SEGMENTS_COUNT("untrackedSegmentsCount", false),

  // Metric used to track errors during the periodic table retention management
  RETENTION_MANAGER_ERROR("retentionManagerError", false),

  // Gauge to reflect whether pauseless is enabled or not
  PAUSELESS_CONSUMPTION_ENABLED("pauselessConsumptionEnabled", false),

  // Metric used to track when segments in error state are detected for pauseless table
  PAUSELESS_SEGMENTS_IN_ERROR_COUNT("pauselessSegmentsInErrorCount", false),

  // Metric used to track when segments in error state are detected for pauseless table for which needs
  // manual intervention for repair
  PAUSELESS_SEGMENTS_IN_UNRECOVERABLE_ERROR_COUNT("pauselessSegmentsInUnrecoverableErrorCount", false),

  // ZK JUTE max buffer size in bytes
  ZK_JUTE_MAX_BUFFER("zkJuteMaxBuffer", true),

  // Bytes to be read from deep store
  DEEP_STORE_READ_BYTES_IN_PROGRESS("deepStoreReadBytesInProgress", true),
  // Count of deep store segment downloads that are currently in progress
  DEEP_STORE_READ_OPS_IN_PROGRESS("deepStoreReadOpsInProgress", true),
  // Bytes to be written to deep store
  DEEP_STORE_WRITE_BYTES_IN_PROGRESS("deepStoreWriteBytesInProgress", true),
  // Count of deep store segment writes that are currently in progress
  DEEP_STORE_WRITE_OPS_IN_PROGRESS("deepStoreWriteOpsInProgress", true);

  private final String _gaugeName;
  private final String _unit;
  private final boolean _global;

  ControllerGauge(String unit, boolean global) {
    _unit = unit;
    _global = global;
    _gaugeName = Utils.toCamelCase(name().toLowerCase());
  }

  @Override
  public String getGaugeName() {
    return _gaugeName;
  }

  @Override
  public String getUnit() {
    return _unit;
  }

  /**
   * Returns true if the gauge is global (not attached to a particular resource)
   *
   * @return true if the gauge is global
   */
  @Override
  public boolean isGlobal() {
    return _global;
  }
}
