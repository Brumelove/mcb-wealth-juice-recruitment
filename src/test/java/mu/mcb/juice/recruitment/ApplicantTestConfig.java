package mu.mcb.juice.recruitment;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class contains basic spring configuration in order to run an integration test successfully
 *
 * @author Brume
 */
@ComponentScan(basePackages = {"mu.mcb.juice.recruitment"})
@EnableJpaRepositories(basePackages = "mu.mcb.juice.recruitment.repository")
@EntityScan(basePackages = "mu.mcb.juice.recruitment.entity")
public class ApplicantTestConfig {

}
