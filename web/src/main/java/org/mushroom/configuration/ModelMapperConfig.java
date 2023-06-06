//package org.mushroom.configuration;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.spi.MatchingStrategy;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySources;
//
//@Configuration
//@ConfigurationProperties(prefix = "model")
//@Getter
//@Setter
//public class ModelMapperConfig {
//
////    private MatchingStrategy matchingStrategy;
//    private boolean skipNullEnabled;
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
//        modelMapper.getConfiguration().setSkipNullEnabled(skipNullEnabled);
//        return modelMapper;
//    }
//
//}
//
