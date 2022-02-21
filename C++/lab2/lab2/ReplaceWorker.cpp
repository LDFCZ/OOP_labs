#include "ReplaceWorker.h"

namespace MainWorkers {

	ReplaceWorker::ReplaceWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {

		if (args.size() != CONSTSPACE::REPLACE_ARG_COUNT) {
			std::string error_text = CONSTSPACE::REPLACEWORKER_ARG_ERROR;
			throw ErrorsSpace::BadArguments(error_text.c_str());
		}

		_replaceable = args[CONSTSPACE::REPLACEABLE_WORD_ARG];
		_substitute = args[CONSTSPACE::SUBSTITUTE_WORD_ARG];
	}

	void ReplaceWorker::work() {

		std::wstring text = _context.get_text(*this);

		size_t index = 0;

		while ((index = text.find(_replaceable, index)) != std::string::npos) {
			text.replace(index, _replaceable.length(), _substitute);
			index += _substitute.length();
		}

		_context.set_text(*this, text);
	}

	Interfaces::Types ReplaceWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types ReplaceWorker::get_output_type() const {
		return _out_type;
	}
}