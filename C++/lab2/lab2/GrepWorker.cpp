#include "GrepWorker.h"

namespace MainWorkers {

	GrepWorker::GrepWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {

		if (args.size() != CONSTSPACE::GREP_ARG_COUNT) {
			std::string error_text = CONSTSPACE::GREPWORKER_ARG_ERROR;
			throw ErrorsSpace::BadArguments(error_text.c_str());
		}

		_grep_word = args[CONSTSPACE::GREP_WORD_ARG];
	}

	void GrepWorker::work() {
		
		std::wstring text = _context.get_text(*this);

		std::wstring new_text = CONSTSPACE::EMPTY_STR;

		std::wstring str = CONSTSPACE::EMPTY_STR;
		for (size_t i = 0; i < text.size(); i++) {
			if (text[i] != CONSTSPACE::ENDL) {
				str += text[i];
			}
			else {
				if (str.find(_grep_word) != std::string::npos) {
					new_text += str + CONSTSPACE::ENDL;
				}
				str = CONSTSPACE::EMPTY_STR;
			}
		}

		_context.set_text(*this, new_text);
	}

	Interfaces::Types GrepWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types GrepWorker::get_output_type() const {
		return _out_type;
	}
}