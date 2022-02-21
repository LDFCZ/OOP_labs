#pragma once

#include <list>

#include "Worker.h"

namespace Interfaces {

	class WorkflowParser {
		public:
		virtual std::vector<int> get_ids_queue() const = 0;

		virtual std::map<int, Interfaces::Worker*> parse_file() = 0;
	};
}