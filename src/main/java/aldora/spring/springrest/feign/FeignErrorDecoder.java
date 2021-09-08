//package aldora.spring.springrest.feign;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//@Component
//public class FeignErrorDecoder implements ErrorDecoder {
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        switch (response.status()) {
//            case 400:
//                return new ResponseStatusException(
//                        HttpStatus.valueOf(response.status()),
//                        "oops, 400 exception"
//                );
//            case 404:
//                return new ResponseStatusException(
//                        HttpStatus.valueOf(response.status()),
//                        "oops, 404 exception"
//                );
//            default:
//                return new Exception(response.reason());
//        }
//    }
//}
