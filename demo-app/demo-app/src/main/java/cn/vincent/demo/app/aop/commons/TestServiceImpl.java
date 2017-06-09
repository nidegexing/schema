/*
 * Copyright (c) 2015 by vincent.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.vincent.demo.app.aop.commons;

import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 * @author Vincent
 * @version 1.0 2017/05/19 14:22
 */
public class TestServiceImpl implements TestService {

    @Override
    public boolean add(String content) {
        System.out.println("add method in TestServiceImpl :" + content);
        if (StringUtils.isNotBlank(content) && "t".equalsIgnoreCase(content)) {
            throw new RuntimeException("故意抛出去的");
        }
        return false;
    }

    @Override
    public boolean update(String content) {
        System.out.println("update method in TestServiceImpl :" + content);
        if (StringUtils.isNotBlank(content) && "t".equalsIgnoreCase(content)) {
            throw new RuntimeException("故意抛出去的");
        }
        return false;
    }
}
