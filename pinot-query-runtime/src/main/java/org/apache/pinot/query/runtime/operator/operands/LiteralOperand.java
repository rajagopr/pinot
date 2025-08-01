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
package org.apache.pinot.query.runtime.operator.operands;

import java.util.List;
import org.apache.pinot.common.utils.DataSchema.ColumnDataType;
import org.apache.pinot.query.planner.logical.RexExpression;


public class LiteralOperand implements TransformOperand {
  private final ColumnDataType _resultType;
  private final Object _value;

  public LiteralOperand(RexExpression.Literal rexExpression) {
    _resultType = rexExpression.getDataType();
    _value = rexExpression.getValue();
  }

  @Override
  public ColumnDataType getResultType() {
    return _resultType;
  }

  @Override
  public Object apply(List<Object> row) {
    return _value;
  }
}
