#include "WriterWorker.h"

namespace MainWorkers {

	WriterWorker::WriterWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {

		if (args.size() != CONSTSPACE::WRITE_ARG_COUNT) {
			std::string error_text = CONSTSPACE::WRITEWORKER_ARG_ERROR;
			throw ErrorsSpace::BadArguments(error_text.c_str());
		}

		try {
			_file_name = args[CONSTSPACE::FILE_NAME_ARG];
			const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
			_file.exceptions(std::ios::failbit | std::ios::badbit);
			_file.open(_file_name);
			_file.imbue(utf8_locale);
		}
		catch (std::ios::failure) {
			std::string error_text = CONSTSPACE::INPUT_FILE_WRITERWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	WriterWorker::~WriterWorker() {
		_file.close();
	}

	void WriterWorker::work() {
		try {
			std::wstring text = _context.get_text(*this);
			_file << text;
			text = CONSTSPACE::EMPTY_STR;
			_context.set_text(*this, text);
		}
		catch (...) {
			std::string error_text = CONSTSPACE::INPUT_FILE_WRITERWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	Interfaces::Types WriterWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types WriterWorker::get_output_type() const {
		return _out_type;
	}
}