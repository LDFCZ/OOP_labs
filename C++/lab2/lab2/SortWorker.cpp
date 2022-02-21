#include "SortWorker.h"

namespace MainWorkers {

	SortWorker::SortWorker(std::vector<std::wstring>& args, MainContext::Context& context) : _context(context) {

		if (args.size() != CONSTSPACE::SORT_ARG_COUNT) {
			std::string error_text = CONSTSPACE::SORTWORKER_ARG_ERROR;
			throw ErrorsSpace::BadArguments(error_text.c_str());
		}
	}

	void SortWorker::work() {

		std::vector<std::wstring> arr;
		std::wstring text = _context.get_text(*this);

		std::wstring new_text = CONSTSPACE::EMPTY_STR;

		std::wstring str = CONSTSPACE::EMPTY_STR;
		for (size_t i = 0; i < text.size(); i++) {
			if (text[i] != CONSTSPACE::ENDL) {
				str += text[i];
			}
			else {
				
				arr.push_back(str + CONSTSPACE::ENDL);
				str = CONSTSPACE::EMPTY_STR;
			}
		}

		std::sort(arr.begin(), arr.end());

		for (size_t i = 0; i < arr.size(); i++) {
			new_text += arr[i];
		}
		_context.set_text(*this, new_text);
	}

	Interfaces::Types SortWorker::get_input_type() const {
		return _in_type;
	}

	Interfaces::Types SortWorker::get_output_type() const {
		return _out_type;
	}
}