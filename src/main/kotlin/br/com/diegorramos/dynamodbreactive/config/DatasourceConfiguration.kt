package br.com.diegorramos.dynamodbreactive.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

@Configuration
class DatasourceConfiguration(
        @Value("\${aws.accessKey}")
        val accessKey: String,
        @Value("\${aws.secretKey}")
        val secretKey: String,
        @Value("\${dynamodb.endpoint}")
        val dynamoEndpoint: String
) {

    @Bean
    fun awsBasicCredentials(): AwsBasicCredentials {
        return AwsBasicCredentials.create(accessKey, secretKey)
    }

    @Bean
    fun dynamoDbAsyncClient(awsBasicCredentials: AwsBasicCredentials): DynamoDbAsyncClient {
        val builder = DynamoDbAsyncClient.builder()
        builder.credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
        if (dynamoEndpoint.isNotBlank()) {
            builder.endpointOverride(URI.create(dynamoEndpoint))
        }
        return builder.build()
    }
}