package org.springsupport.tools.argument.parser;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import org.springframework.stereotype.Component;

@Component
public class LoopArgumentParser implements ArgumentParser<ArgumentMetadata> {

	@Override
	public boolean canParse(@NotNull String... args) {
		return args.length == 0 || args.length > 1 && args.length % 2 == 0;
	}

	@Override
	public List<ArgumentMetadata> parse(@NotNull String... args) throws ArgumentParseException {
		if(!canParse(args)) {
			String argsJoined = String.join(" ", args);
			throw new ArgumentParseException("invalid argument size: " + args.length, argsJoined);
		}

		List<ArgumentMetadata> argumentMetadata = new ArrayList<>();
		for(int i = 0; i < args.length; i += 2) {
			argumentMetadata.add(new ArgumentMetadata(
					args[i], args[i + 1]
			));
		}
		return argumentMetadata;
	}

}
