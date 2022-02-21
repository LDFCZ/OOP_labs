#include "ReaderWorker.h"

namespace MainWorkers {

	ReaderWorker::ReaderWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {

		if (args.size() != CONSTSPACE::READ_ARG_COUNT) {
			std::string error_text = CONSTSPACE::READWORKER_ARG_ERROR;
			throw ErrorsSpace::BadArguments(error_text.c_str());
		}

		_file_name = args[CONSTSPACE::FILE_NAME_ARG];

		const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
		_file.open(_file_name);
		_file.imbue(utf8_locale);

		if (!_file.is_open()) {
			std::string error_text = CONSTSPACE::OUTPUT_FILE_RIDERWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	ReaderWorker::~ReaderWorker() {
		_file.close();
	}

	void ReaderWorker::work() {
		try {
			std::wstring text = _context.get_text(*this);

			std::wstring str;

			while (_file)
			{
				getline(_file, str);
				str += CONSTSPACE::ENDL;
				if (!_file) {
					break;
				}
				text += str;
			}

			_context.set_text(*this, text);

		} 
		catch (...) {
			std::string error_text = CONSTSPACE::OUTPUT_FILE_RIDERWORKER_ERROR + Helpers::convert_to_string(_file_name);
			throw ErrorsSpace::BadInputFile(error_text.c_str());
		}
	}

	Interfaces::Types ReaderWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types ReaderWorker::get_output_type() const {
		return _out_type;
	}
}