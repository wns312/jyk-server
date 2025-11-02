package jyk.template.controller

import jyk.template.controller.dto.TestRequest
import jyk.template.controller.dto.TestResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController {

    @PostMapping("/test")
    suspend fun testRequest(@RequestBody request: TestRequest): TestResponse {
        return TestResponse(request.test.repeat(3))
    }
}
