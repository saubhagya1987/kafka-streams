package com.kaviddiss.streamkafka;




import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableBinding(Source.class)
public class EdmpSampleStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdmpSampleStreamApplication.class, args);
	}

	/*@Bean
	 @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<TimeInfo> messageSource() {
		return () -> MessageBuilder.withPayload(new TimeInfo(new Date().getTime()+"","Label")).build();
	}*/
	
	public static class TimeInfo{
		
		private String time;
		private String label;
		
		public TimeInfo(String time, String label) {
			super();
			this.time = time;
			this.label = label;
		}

		public String getTime() {
			return time;
		}

		public String getLabel() {
			return label;
		}
		
	}
	

@RestController
public class GreetingsController {
   
	@Autowired
    private Source source;
	
    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("time") String time) {
    	try {
           /* source.output().send(MessageBuilder
                    .withPayload(time).build());*/
    		source.output().send(MessageBuilder.withPayload(new TimeInfo(new Date().getTime()+"",time)).build());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

}


