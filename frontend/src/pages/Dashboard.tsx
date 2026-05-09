import React from 'react';
import { useQuery } from 'react-query';
import { getPolicies, getClaims, getBillingHistory } from '../services/api';
import PolicyCard from '../components/PolicyCard';
import ClaimsTable from '../components/ClaimsTable';
import BillingChart from '../components/BillingChart';

const Dashboard: React.FC = () => {
  const { data: policies, isLoading: policiesLoading } = useQuery('policies', getPolicies);
  const { data: claims } = useQuery('claims', getClaims);

  if (policiesLoading) return <div className="spinner">Loading your portal...</div>;

  return (
    <div className="dashboard">
      <h1>Welcome back, {policies?.[0]?.customerName}</h1>

      <section className="policies-grid">
        <h2>Your Policies</h2>
        {policies?.map(policy => (
          <PolicyCard key={policy.id} policy={policy} />
        ))}
      </section>

      <section className="claims-section">
        <h2>Recent Claims</h2>
        <ClaimsTable claims={claims?.slice(0, 5) ?? []} />
      </section>

      <section className="billing-section">
        <h2>Payment History</h2>
        <BillingChart />
      </section>
    </div>
  );
};

export default Dashboard;
