/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.foo.controller;

import com.foo.controller.dto.NormalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Web {@link CustomErrorRestController} used to generate a REST API.
 *
 * @author Greg Turnquist
 */
@RestController
public class CustomErrorRestController {

	@GetMapping(path="/throw/{id}", produces = "application/json")
	public ResponseEntity throwError(@PathVariable("id") Integer value) {

		if ( value == null )
			throw new IllegalArgumentException("Null Input");

		int val = value.intValue();
		switch (val) {
			case 0:
				return ResponseEntity.ok(NormalDto.of());
			case 1:
				throw new SomeServiceException();
			case 9:
				// Division by 0
				val = val / (val - 9);
				return ResponseEntity.ok(NormalDto.of());
			default:
				throw new IllegalArgumentException("Unexpected value");
		}


	}


}
