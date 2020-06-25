package com.dfl.newsapi.builder


class NewsQuery private constructor(val query: String) {

    companion object{
        private const val SPACE = " "
        private const val AND = "AND"
        private const val OR = "OR"
        private const val NOT = "NOT"
        private const val MUST_NOT = "-"
        private const val MUST = "+"
        private const val EXACT_OPENING = "\""
        private const val EXACT_CLOSING = EXACT_OPENING
    }

    class NewsQueryBuilder {
        private val query = StringBuilder()

        fun with(value: String): NewsQueryBuilder {
            query.append(value)
            return this
        }

        fun or(value: String): NewsQueryBuilder {
            checkQuery()
            query.append("$SPACE$OR$SPACE$value")
            return this
        }

        fun orAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.or(it)
            }
            return this
        }

        fun and(value: String): NewsQueryBuilder {
            checkQuery()
            query.append("$SPACE$AND$SPACE$value")
            return this
        }

        fun andAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.and(it)
            }
            return this
        }

        fun not(value: String): NewsQueryBuilder {
            checkQuery()
            query.append("$SPACE$NOT$SPACE$value")
            return this
        }

        fun notAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.not(it)
            }
            return this
        }

        fun must(value: String): NewsQueryBuilder {
            query.append("$SPACE$MUST$SPACE$value")
            return this
        }

        fun mustAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.must(it)
            }
            return this
        }

        fun mustNot(value: String): NewsQueryBuilder {
            query.append("$SPACE$MUST_NOT$SPACE$value")
            return this
        }

        fun mustNotAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.mustNot(it)
            }
            return this
        }

        fun exact(value: String): NewsQueryBuilder {
            query.append("$SPACE$EXACT_OPENING$value$EXACT_CLOSING")
            return this
        }

        fun exactAll(list: List<String>): NewsQueryBuilder {
            list.forEach {
                this.exact(it)
            }
            return this
        }

        fun build(): NewsQuery = NewsQuery(this.query.toString())

        private fun checkQuery() {
            if (query.isEmpty()) throw IllegalStateException("Cannot append conditions before NewsQueryBuilder.with()")
        }
    }


}

