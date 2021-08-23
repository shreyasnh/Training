class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if not prices: return 0
        lp, p = prices[0], 0

        for i in range(1, len(prices)):
            p = max(p, prices[i]-lp)
            lp = min(lp, prices[i])

        return p
