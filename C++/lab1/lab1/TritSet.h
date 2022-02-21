#pragma once

#include "Trit.h"

#include <vector>
#include <unordered_map>

typedef unsigned int uint;

namespace TernaryLogic {

	class TritSet
	{
	private:
		std::vector<uint> _array;
		size_t _array_size;
		size_t _alloc_size;
		size_t _logical_size;

		size_t _true_count = 0;
		size_t _false_count = 0;
		size_t _unknown_count = 0;

		void _set_Trit(Trit, size_t);

		void _change_TritSet_size(long long);

		long long _get_alloc_size(long long);

		Trit _get_Trit(size_t) const;

		long long _search_last_index() const;

	public:
		class Reference {
			friend class TritSet;
		public:
			TritSet& operator = (Trit);
			operator Trit() const; //explicit
		private:
			Reference(TritSet&, size_t);
			TritSet& _set;
			size_t _position;
		};

		TritSet(size_t = 0, Trit = UNKNOWN);

		TritSet(const TritSet&);

		TritSet& operator=(const TritSet&);

		TritSet(TritSet&&);

		TritSet& operator=(TritSet&&);

		//~TritSet();

		Reference operator[](size_t);
		
		Trit operator[](size_t) const;

		
		
		

		size_t cardinality(Trit) const;

		std::unordered_map<Trit, size_t, std::hash<size_t>> cardinality() const;


		// Returns logical size of Trit Set (getter)
		size_t size() const;

		// Capacity.... (getter)
		size_t capacity() const;

		void shrink();

		void trim(size_t);

	};

	TritSet operator & (const TritSet& tr_set1, const TritSet& tr_set2);
	TritSet operator | (const TritSet& tr_set1, const TritSet& tr_set2);
	TritSet operator ~ (const TritSet& tr_set);

	bool operator == (const TritSet& tr_set1, const TritSet& tr_set2);
	bool operator != (const TritSet& tr_set1, const TritSet& tr_set2);
}
