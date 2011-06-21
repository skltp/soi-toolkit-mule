/* 
 * Licensed to the soi-toolkit project under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The soi-toolkit project licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.soitoolkit.tools.generator.util;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.soitoolkit.tools.generator.GeneratorUtil;
import org.soitoolkit.tools.generator.model.enums.TransformerEnum;
import org.soitoolkit.tools.generator.model.enums.TransportEnum;


public class GroovyGeneratorUtilTest {

	private static final String TEST_OUT_FOLDER = PreferencesUtil.getDefaultRootFolder() + "/jUnitTests/GroovyGeneratorUtilTest";
	
	@Test
	public void testGroovyGenerator() throws FileNotFoundException {
		int i = 1;
		assertTrue(i==1);

		GeneratorUtil gu = new GeneratorUtil(System.out, "myGroup", "myArtifact", null, "myService", null, TransportEnum.VM, TransportEnum.VM, TransformerEnum.JAVA, "template-folder", TEST_OUT_FOLDER);
		gu.generateContentAndCreateFileUsingGroovyGenerator(getClass().getResource("MyTestGroovyGenerator.groovy"), "myGroovyoutput.txt");
		
		InputStream is = new FileInputStream(TEST_OUT_FOLDER + "/" + "myGroovyoutput.txt");
		String content = MiscUtil.convertStreamToString(is);
		System.err.println(content);
	}
}
