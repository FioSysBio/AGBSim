import cobra.test
import numpy as np

def eat(metabolites, directions, v_in, metabolic_model, time, c_in):
    print('-----------------------------')
    try:
        metabolites = metabolites[0]
        directions = np.array(directions)
        c_in = np.array(c_in)
        v_in = np.array(v_in)
        metabolic_model = str(metabolic_model)
        time = np.array(time)
        # The Michaelis–Menten kinetic equation proposed for substrate by (Bauer et al., 2017)
        # v_in=-7.56*c_in/(0.01+c_in)
        solver = 'glpk'
        cobra.test.create_test_model(metabolic_model)
        model = cobra.test.create_test_model(metabolic_model)
        v_out = np.zeros(len(v_in)+1)

        for i in range(len(v_in)):
            if directions[i] == 0:
                v_out[i] = 0
            else:
                if directions[i] == 1:
                    model.reactions.get_by_id(metabolites[i]).lower_bound = v_in[i]
                elif directions[i] == -1:
                    model.reactions.get_by_id(metabolites[i]).upper_bound = v_in[i]

        s = model.optimize()
        print(s.fluxes)

        for j in range(len(v_in)):
            if directions[j] != 0:
                v_out[j] = s.fluxes[model.reactions.get_by_id(metabolites[j]).id]
                print(v_out[j])

        miu = s.objective_value
        v_out[len(v_out)-1] = miu
        print(v_out)
    except Exception as e:
        print(e)