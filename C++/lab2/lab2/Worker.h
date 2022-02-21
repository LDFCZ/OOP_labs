#pragma once

#include <string>
#include <vector>
#include <codecvt>
#include <fstream> 

#include "CONSTSPACE.h"
#include "BadSyntax.h"
#include "BadInputFile.h"
#include "Helpers.h"
#include "BadArguments.h"

namespace Interfaces {

	enum class Types {EMPTY = 0, TEXT};

	class Worker {
	public:
		virtual void work() = 0;

		virtual Types get_input_type() const = 0;

		virtual Types get_output_type() const = 0;
	};
}