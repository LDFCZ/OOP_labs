#pragma once

#include <vector>
#include <string>

#include "Context.h"
#include "FileReader.h"

namespace MainClasses {

	class StringParser
	{
	private:
		Container::Context& words_context;
		FileInterface::FileReader* ifile;
	public:
		StringParser(Container::Context& words_context, FileInterface::FileReader* ifile);

		void parse_strings();
	};
}

