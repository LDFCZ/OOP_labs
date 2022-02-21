#include "DumpWorker.h"

namespace MainWorkers {

	DumpWorker::DumpWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {
		if (args.size() != CONSTSPACE::DUMB_ARG_COUNT) {
			std::string error_text = CONSTSPACE::DUMPWORKER_ARG_ERROR;
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
			std::string error_text = CONSTSPACE::OUTPUT_FILE_DUMPWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	DumpWorker::~DumpWorker() {
		_file.close();
	}

	void DumpWorker::work() {
		try {
			std::wstring text = _context.get_text(*this);
			_file << text;
			_context.set_text(*this, text);
		}
		catch (...) {
			std::string error_text = CONSTSPACE::OUTPUT_FILE_DUMPWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	Interfaces::Types DumpWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types DumpWorker::get_output_type() const {
		return _out_type;
	}
}